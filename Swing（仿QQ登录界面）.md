## 实现类似于QQ的简单界面，登录时会通过本地数据库验证信息是否正确。
- (部分代码来自网络)
- 界面：

     ![界面](http://a2.qpic.cn/psb?/V10iTWVA1ErXb0/rJsl8MUHzP7nf1BrJ8*I3ETIg3af4E96mR5GVCVQ7W8!/b/dD8BAAAAAAAA&bo=YwEJAQAAAAADB0g!&rf=viewer_4)

- 登陆成功会弹出另一个界面

    ![相应弹出界面](http://a2.qpic.cn/psb?/V10iTWVA1ErXb0/02.lnul.1wHadRrZaeiDO4gigzV5.Rk64rRAERSQrnA!/b/dNAAAAAAAAAA&bo=3AD0AQAAAAADAAw!&rf=viewer_4)
    
- 帐号密码错误会报错
    ![报错](http://a2.qpic.cn/psb?/V10iTWVA1ErXb0/o0Uoa6hrfeGxSd40tVDXB1kfD4S21KTFzIcAf0BIc*s!/b/dD8BAAAAAAAA&bo=YwEJAQAAAAADAE8!&rf=viewer_4)
    
- ### 类test55
```
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class test55 extends JFrame{
	
	private JTextField text1 ;
	private JPasswordField pwd ;
	
	// 小容器
    private JLabel jl1;
    private JLabel jl2;
    private JLabel jl3;
    private JLabel jl4;
    // 小按钮
    private JButton bu2;
    private JButton bu3;
    private JButton btn1;
 
    // 复选框
    private JCheckBox jc1;
    private JCheckBox jc2;
 
    // 列表框
    private JComboBox jcb;
    
    PoliceListen listener;
    
	public test55(){
		this.setTitle("QQ for ca");//标题
		
		init();//窗体组件初始化
		
		this.setBounds(0, 0,355 , 265);//窗体大小
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭按钮
		
		this.setLayout(null);//布局为绝对布局

		this.setResizable(false);//窗体大小不可变
		
		this.setLocationRelativeTo(null);//居中显示
		
		this.setVisible(true);//窗口可见
		
		Image image = new ImageIcon("F:/bb/qq1.png").getImage();
		this.setIconImage(image);//设置窗体的标题图片
	}
	void init(){
		// 创建一个容器
        Container con = this.getContentPane();
        jl1 = new JLabel();
        // 设置背景图片
        Image image1 = new ImageIcon("F:/bb/111.jpg").getImage();
        jl1.setIcon(new ImageIcon(image1));
        jl1.setBounds(0, 0, 355, 265);
        // QQ登录头像设定
        jl2 = new JLabel();
        Image image2 = new ImageIcon("F:/bb/123.png").getImage();
        jl2.setIcon(new ImageIcon(image2));
        jl2.setBounds(40, 95, 50, 60);
        //帐号
		text1 = new JTextField(10);
		text1.setBounds(100, 100, 150, 20);
		// 用户号码登录输入框旁边的文字
		jl3 = new JLabel("注册账号");
        jl3.setBounds(260, 100, 70, 20);
        //密码
		pwd = new JPasswordField(10);
		pwd.setBounds(100, 130, 150, 20);
		
		// 密码输入框旁边的文字
        jl4 = new JLabel("找回密码");
        jl4.setBounds(260, 130, 70, 20);
        // 输入框下方文字
        jc1 = new JCheckBox("记住密码");
        jc1.setBounds(105, 155, 80, 15);
        jc2 = new JCheckBox("自动登录");
        jc2.setBounds(185, 155, 80, 15);
        // 用户登录状态选择
        jcb = new JComboBox();
        jcb.addItem("在线");
        jcb.addItem("隐身");
        jcb.addItem("离开");
        jcb.setBounds(40, 150, 55, 20);
        
        btn1 = new JButton("登录");
        btn1.setBounds(280, 200, 65, 20);
        
		listener = new PoliceListen();
		btn1.addActionListener(listener);//给按钮加监听
		
		bu2 = new JButton("多账号");
        bu2.setBounds(5, 200, 75, 20);
        bu3 = new JButton("设置");
        bu3.setBounds(100, 200, 65, 20);
 
        // 所有组件用容器装载
        jl1.add(jl2);
        jl1.add(jl3);
        jl1.add(jl4);
        jl1.add(jc1);
        jl1.add(jc2);
        jl1.add(jcb);
        jl1.add(btn1);
        jl1.add(bu2);
        jl1.add(bu3);
        
        con.add(jl1);
        con.add(text1);
        con.add(pwd);
        
	}
	public class PoliceListen implements ActionListener{

	
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource() == btn1){
				T44 t4 = new T44();
				String s1 = text1.getText();
				String pw1 = pwd.getText();
				//System.out.println(s1);
				//System.out.println(pw1);
				Connection con = t4.getConn();
				t4.testSQL(s1, pw1);
				if(t4.nn==1){
					QQ();
				}else{
					JOptionPane.showMessageDialog(null, "对不起您的用户名或密码错误!", "错误",JOptionPane.ERROR_MESSAGE);
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*
				if(s1.equals("xxxxxx") && pw1.equals("123456")){
					QQ();                                                                     
				   } else {
				    JOptionPane.showMessageDialog(null, "对不起您的用户名或密码错误!", "错误",JOptionPane.ERROR_MESSAGE);
				   }//JOptionPane.showMessageDialog,弹出消息框
				   */
				}
			}
		}
	
	public void QQ(){
		JFrame qq = new JFrame("QQ");
		Container con = qq.getContentPane();
		
		con.setBackground(Color.BLUE);
        qq.setBounds(60,100, 220, 500); 
        
		qq.setVisible(true);
		qq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test55  t5 = new test55();	
	}

}

```
- ### T44
```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class T44 {
	int nn;
	 String una = null;
	 String upw = null;
	 String url = "jdbc:oracle:thin:@localhost:1521:ORCL";//连接oracle的url
	 String user="cajava";		//账户名
	 String psw ="123456";	//账户密码
	 Connection conn = null;	//创建连接
	public  Connection getConn(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //连接驱动的目录名
			conn = DriverManager.getConnection(url,user,psw);//通过驱动获得连接
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public  void testSQL(String na,String pw){
		if(conn != null){
			int i =0;
			try {
				String sql="select * from Users where Uname='"+ na +"' and str = '"+ pw+"'";
				Statement sm =  conn.createStatement();
				ResultSet n = sm.executeQuery(sql);
				while (n.next()){
					i=i+1;
					/*
					 * System.out.println(n.getString(1)+"  "); //gitxxx函数是获取字段值
					 * System.out.println(n.getString(2)+"  ");   
					System.out.println(n.getBigDecimal(3)+"  ");
					 */
					
				}
				n.close();
				sm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==1){
				System.out.println("验证成功");
				nn=1;
			}else{
				System.out.println("验证失败");
				nn=2;
			}
			
		}
	}
}

```





