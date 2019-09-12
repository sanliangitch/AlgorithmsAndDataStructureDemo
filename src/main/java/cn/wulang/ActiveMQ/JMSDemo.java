package cn.wulang.ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author wulang
 * @create 2019/6/22/15:26
 */
public class JMSDemo {
    public static final String ACTIVEMQ_URL = "tcp://148.70.34.49:8162";
    public static final String QUEUE_NAME = "queue01";
    public static void main(String[] args) throws JMSException, IOException {
        //1、创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2、通过连接工程获取连接，并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3、创建回话session
        //一个叫事务，一个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);
        //Collections collections = new ArrayList<>();

        //5、创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //6、通过使用MessageProducer生产消息发送到MQ队列里面
        for (int i =1 ;i<3;i++){

            //7、创建消息
            TextMessage textMessage = session.createTextMessage("msg------" + i);
            textMessage.setJMSMessageID("");
            //8、通过MessageProducer发送给mq
            messageProducer.send(textMessage);
        }
        //9、关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发送到mq完成");

//------------------------------------------------------------------------------------------------------------------------------------------
        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

        /**
         * 同步阻塞（receive()）
         * 订阅者或者消费者调用MessageConsumer的receive()方法来接收消息，receive方法在能接收消息之前（或超时之前）将一直阻塞
         * while (true){
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if (null != textMessage){
                System.out.println(textMessage);
            }else {
                break;
            }
            messageConsumer.close();
            session.close();
            connection.close();
         }
            */
        //通过监听的方式来接收消息  Session session = connection.createSession(queue);

        //异步非阻塞方式（监听器onMessage()）
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                //传什么消费什么
                if (message !=null && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
 //----------------------------------------------------------------------------------------------------------------------

}
