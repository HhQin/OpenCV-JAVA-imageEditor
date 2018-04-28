package helloworld1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ImageEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageEditor frame = new ImageEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String imgPath="";
	public String imgName="";
	public String storePath="";

	/**
	 * Create the frame.
	 */
	public ImageEditor() {
		setResizable(false);
		
		
		setTitle("\u57FA\u4E8EOpenCV\u7684\u56FE\u50CF\u5904\u7406\u5DE5\u5177");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblopencv = new JLabel("\u57FA\u4E8EOpenCV\u7684\u56FE\u50CF\u5904\u7406\u5DE5\u5177");
		lblopencv.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 29));
		lblopencv.setBounds(297, 13, 402, 31);
		contentPane.add(lblopencv);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u8981\u5904\u7406\u7684\u56FE\u7247\u6587\u4EF6");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(14, 57, 229, 31);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("(\u8BF7\u9009\u62E9\u56FE\u7247\u6587\u4EF6)");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(403, 65, 544, 18);
		contentPane.add(label);
		
		
		JButton button = new JButton("\u6D4F\u89C8\u76EE\u5F55\u2026");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jFileChooser = new JFileChooser();
				int i = jFileChooser.showOpenDialog(null);
				 if(i== jFileChooser.APPROVE_OPTION){ //打开文件
                     imgPath = jFileChooser.getSelectedFile().getAbsolutePath();
                     imgName = jFileChooser.getSelectedFile().getName();
                     label.setText("当前文件路径："+imgPath);
                 }else{
                     label.setText("没有选中文件，请重新选择");
                 }
				
			}
		});
		button.setBounds(257, 61, 132, 27);
		contentPane.add(button);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u9009\u62E9\u5B58\u50A8\u76EE\u5F55");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(96, 101, 147, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("(\u8BF7\u9009\u62E9\u5B58\u50A8\u76EE\u5F55)");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(403, 101, 609, 18);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("\u6D4F\u89C8\u76EE\u5F55\u2026");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser1 = new JFileChooser();
				jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int i = jFileChooser1.showOpenDialog(null);
				 if(i== jFileChooser1.APPROVE_OPTION){ //打开文件夹
                     storePath = jFileChooser1.getSelectedFile().getPath();
                     label_1.setText("要保存的文件夹路径："+storePath);
                 }else{
                     label_1.setText("没有选中文件，请重新选择");
                 }
				
			}
			
		});
		btnNewButton.setBounds(257, 101, 132, 27);
		contentPane.add(btnNewButton);
		
		JLabel label_2 = new JLabel("\u56FE\u7247\u4E8C\u503C\u5316");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(133, 178, 110, 27);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(422, 178, 169, 18);
		contentPane.add(label_3);
		
		JButton btnNewButton_1 = new JButton("\u70B9\u51FB\u64CD\u4F5C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mat img =  Imgcodecs.imread(imgPath);
		        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);
		        Imgproc.adaptiveThreshold(img, img, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 25, 10);
		        Imgcodecs.imwrite(storePath+"\\erzhihua.jpg", img);
		        label_3.setText("操作成功！");
			}
		});
		btnNewButton_1.setBounds(257, 176, 132, 27);
		contentPane.add(btnNewButton_1);
		
		JLabel label_4 = new JLabel("\u56FE\u7247\u9510\u5316");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(158, 236, 85, 18);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("宋体", Font.PLAIN, 18));
		label_5.setBounds(422, 236, 169, 18);
		contentPane.add(label_5);
		
		JButton button_1 = new JButton("\u70B9\u51FB\u64CD\u4F5C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mat img =  Imgcodecs.imread(imgPath);
				Mat kernal = new Mat(3,3,CvType.CV_32FC1, new Scalar(0));
		        kernal.put(0, 1, -1);
		        kernal.put(1, 0, -1);
		        kernal.put(1, 1, 5);
		        kernal.put(1, 2, -1);
		        kernal.put(2, 1, -1);
		        Imgproc.filter2D(img, img, img.depth(), kernal);
		        Imgcodecs.imwrite(storePath+"\\ruihua.jpg", img);
		        label_5.setText("操作成功！");
			}
		});
		button_1.setBounds(257, 232, 132, 27);
		contentPane.add(button_1);
		
		JLabel label_6 = new JLabel("\u4EBA\u8138\u8BC6\u522B");
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		label_6.setBounds(158, 349, 85, 18);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setFont(new Font("宋体", Font.PLAIN, 18));
		label_7.setBounds(422, 349, 169, 18);
		contentPane.add(label_7);
		
		JButton button_2 = new JButton("\u70B9\u51FB\u64CD\u4F5C");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CascadeClassifier faceDetector = new CascadeClassifier("C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
		        Mat image = Imgcodecs.imread(imgPath);
		        MatOfRect faceDetections = new MatOfRect();
		        faceDetector.detectMultiScale(image, faceDetections);
		        int temp=0;
		        for (Rect rect : faceDetections.toArray()) {
		            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
		                    + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		            temp++;
		        }

		        String filename = storePath+"\\renlianshibie.jpg";
		        label_7.setText("共检测到"+temp+"个人脸！");
		        Imgcodecs.imwrite(filename, image);
			}
		});
		button_2.setBounds(257, 347, 132, 27);
		contentPane.add(button_2);
		
		JLabel label_8 = new JLabel("\u56FE\u50CF\u7F29\u653E");
		label_8.setFont(new Font("宋体", Font.PLAIN, 20));
		label_8.setBounds(158, 293, 85, 18);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setFont(new Font("宋体", Font.PLAIN, 18));
		label_9.setBounds(506, 293, 147, 25);
		contentPane.add(label_9);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0.3", "0.5", "0.8", "1.5", "2", "2.5", "3"}));
		comboBox.setBounds(257, 292, 59, 24);
		contentPane.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("\u70B9\u51FB\u64CD\u4F5C");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float temp=Float.parseFloat(comboBox.getSelectedItem().toString()); //temp为缩放倍数
				Mat src = Imgcodecs.imread(imgPath);
		        Mat dst = new Mat();
		        Imgproc.resize(src, dst, new Size(src.cols()*temp,src.rows()*temp), 0, 0, Imgproc.INTER_AREA);
		        Imgcodecs.imwrite(storePath+"\\suofang.jpg", dst);
		        label_9.setText("操作成功！");
			}
		});
		btnNewButton_2.setBounds(341, 291, 132, 27);
		contentPane.add(btnNewButton_2);
		
		
	}
}
