
package ejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@MessageDriven(mappedName = "jms/Queue1", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class MessageDrivenBean implements MessageListener {
    @PersistenceContext()
    EntityManager em;
    MemberEntity mem;
    FineEntity fine;

    @Resource(mappedName = "jms/QueueConnectionFactory")
    private ConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/Queue1")
    private Queue queue;


    public MessageDrivenBean() {
    }


    public void onMessage(Message inMessage) {
        try {

            MapMessage msg = null;
            if (inMessage instanceof MapMessage) {
                msg = (MapMessage) inMessage;
                setUpEntities(msg); } 
            else {
                System.out.println("MessageDrivenBean.onMessage: " +
                  "wrong type: " + inMessage.getClass().getName());
            }

        }catch (Exception ie) {
            System.out.println("MessageDrivenBean.onMessage: " +
                               "InterruptedException: " + ie.toString());
        } catch (Throwable te) {
            System.out.println("MessageDrivenBean.onMessage: Exception: " +
              te.toString());
        }
    }

    private void setUpEntities(MapMessage msg) {
        
        Connection connection         = null;
        Session session               = null;
        MessageProducer producer      = null;
        MapMessage replyMsg           = null;
        Destination replyDest         = null;
        String replyCorrelationMsgId  = null;
        boolean done                  = false;
        Connection queueConnection    = null;
        Session queueSession          = null;
        MessageProducer queueProducer = null;
        TextMessage message           = null;
        long memId ;
        long fineId ;
        int amount=0;
        int payAmount=0;
        String cm=new String();
        String rmessage=new String();
       
        try {
            cm=msg.getString("cmd");
            if(cm.equals("Fine"))
            {
            memId= msg.getLong("MemberId");
            System.out.println("MessageDrivenBean.setUpEntities:" +" Message received for member ID " + memId);
            mem = em.find(MemberEntity.class, memId);
            if (mem != null) {
                System.out.println("MessageDrivenBean.setUpEntities: " + "Found entity for memberEntity " + memId);
                fine=mem.getFine();
                if(fine==null)
                {
                    System.out.println("MessageDrivenBean.setUpEntities: "+"No fine owed ");
                    done=true;
                }
                else
                {
                    System.out.println("MessageDrivenBean.setUpEntities: "+ "Found amount "+fine.getAmtOwing());
                    done=true;
                }

            }
            }
            else
            {
                memId=msg.getLong("MemberId");
                payAmount=msg.getInt("Pay");
                rmessage=makeFinePayment(memId,payAmount);
                done=true;
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("MessageDrivenBean.setUpEntities: " + "No entity found");
        } catch (Exception e) {
            System.out.println("MessageDrivenBean.setUpEntities: " +"em.find failed without throwing " +"IllegalArgumentException");
        }

        if(mem==null)
        {
            System.out.println("MessageDrivenBean.setUpEntities: "+"Member doesn't exist");
            done=true;
        }
        if(done)
        {
            try {
                  connection = queueConnectionFactory.createConnection();

            } catch (Exception ex) {
                System.out.println("MessageDrivenBean.setUpEntities: " +"Unable to connect to JMS provider: " + ex.toString());
            }
            try {

                replyDest = msg.getJMSReplyTo();
                replyCorrelationMsgId = msg.getJMSMessageID();

                session  = connection.createSession(true, 0);
                producer = session.createProducer(replyDest);

                if(cm.equals("Fine"))
                {
                replyMsg = createReplyMsg(session, replyCorrelationMsgId);
                producer.send(replyMsg);
                System.out.println("The reply message is "+replyMsg);
                System.out.println("MessageDrivenBean.setUpEntities: " +"Sent reply message for memberId " + mem.getId());
                }
                else
                {
                      message = creatText(session,replyCorrelationMsgId,rmessage);
                      producer.send(message);
                      System.out.println("MessageDrivenBean.setUpEntities: "+"send Payment");
                }

            } catch (JMSException je) {
                System.out.println("MessageDrivenBean.setUpEntities: " +"JMSException: " + je.toString());
            } catch (Exception e) {
                System.out.println("MessageDrivenBean.setUpEntities: " +"Exception: " + e.toString());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException je) {
                        System.out.println("MessageDrivenBean.setUpEntities: " + "JMSException: " + je.toString());
                    }
                    connection = null;
                }
                if (queueConnection != null) {
                    try {
                        queueConnection.close();
                    } catch (JMSException je) {
                        System.out.println("MessageDrivenBean.setUpEntities: " +"JMSException: " + je.toString());
                    }
                    queueConnection = null;
                }
            }
        }
    }

    private MapMessage createReplyMsg(Session session, String msgId) throws JMSException {

          MapMessage replyMsg = session.createMapMessage();

        if(mem==null)
        {
            replyMsg.setString("MemberExist", "No");
            replyMsg.setInt("Amount", 0);
        }
        else
        {
            replyMsg.setString("MemberExist", "Yes");
        if(fine==null)
            replyMsg.setInt("Amount", 0);
        else
        {
            int amount=(int)fine.getAmtOwing();
            replyMsg.setInt("Amount", amount);
        }
        }
        replyMsg.setJMSCorrelationID(msgId);
        return replyMsg;
    }


    private String makeFinePayment(long memId, int pay) {
       try {
            MemberEntity ee=em.find(MemberEntity.class, memId);
            if (ee != null) {
                fine = (FineEntity) ee.getFine();
                PaymentEntity pe=new PaymentEntity();
                pe.create(pay);
                fine.addPayments(pe);
                return "Yes";
            } else {
                return "Member not found.";
            }
        } catch (Exception ex) {ex.printStackTrace();
            return "No";
        }
    }

    private TextMessage creatText(Session session, String msgId, String rm) throws JMSException {
        TextMessage replyMsg=session.createTextMessage(rm);
        replyMsg.setJMSCorrelationID(msgId);
        return replyMsg;
    }


}
