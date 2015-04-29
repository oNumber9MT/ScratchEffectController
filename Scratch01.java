package scratch01;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.net.*;
import java.io.*;

public class Scratch01 extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String HOST_NAME = "localhost";
	private static final int PORT_NUMBER = 42001;
	private static Socket socket; //ソケット
	PrintStream out; //出力ストリーム

	String messageToScratch;
	JLabel labelSize; //大きさ数値
	JSlider sliderSize; //大きさスライダー
	JLabel labelCol; //色数値
	JSlider sliderCol; //色スライダー
	JLabel labelLens; //魚眼数値
	JSlider sliderLens; //魚眼スライダー
	JLabel labelTwi; //渦巻き胃数値
	JSlider sliderTwi; //渦巻きスライダー
	JLabel labelPix; //ピクセル数値
	JSlider sliderPix; //ピクセルスライダー
	JLabel labelMos; //モザイク数値
	JSlider sliderMos; //モザイクスライダー
	JLabel labelLig; //明るさ数値
	JSlider sliderLig; //明るさスライダー
	JLabel labelGst; //幽霊数値
	JSlider sliderGst; //幽霊スライダー
	
	Scratch01() {
		try {
			//ソケット生成
			socket = new Socket(HOST_NAME, PORT_NUMBER);
			//出力ストリーム取得
			out = new PrintStream(socket.getOutputStream());

			//
			/////////////////////////////////////////////
			//クリア
			/////////////////////////////////////////////
			JButton button01 = new JButton("クリア");
			button01.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					messageToScratch = "broadcast \"clear\"";
					sendMessage();
					sliderSize.setValue(100);
					labelSize.setText("100");
					sliderCol.setValue(0);
					labelCol.setText("0");
					sliderLens.setValue(0);
					labelLens.setText("0");
					sliderTwi.setValue(0);
					labelTwi.setText("0");
					sliderPix.setValue(0);
					labelPix.setText("0");
					sliderMos.setValue(0);
					labelMos.setText("0");
					sliderLig.setValue(0);
					labelLig.setText("0");
					sliderGst.setValue(0);
					labelGst.setText("0");
				}
			});
			
			/////////////////////////////////////////////
			//大きさコントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titleSize = new JLabel("SIZE");
			JPanel panelSizeTitle = new JPanel();
			panelSizeTitle.setPreferredSize(new Dimension(30, 20));
			panelSizeTitle.add(titleSize);

			//数値
			labelSize = new JLabel("100");
			JPanel panelSizeLabel = new JPanel();
			panelSizeLabel.setPreferredSize(new Dimension(30, 20));
			panelSizeLabel.add(labelSize);
			
			//スライダー
			sliderSize = new JSlider(0, 500, 100);
			sliderSize.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderSize.getValue();
					messageToScratch = "sensor-update \"effect0\" " + value;
					sendMessage();
					labelSize.setText(String.valueOf(value));
					messageToScratch = "broadcast \"size\"";
					sendMessage();	
				}
			});
			JPanel panelSizeSlider = new JPanel();
			panelSizeSlider.add(sliderSize);
			
			//パネル作成
			JPanel panelSize = new JPanel();
			panelSize.setLayout(new FlowLayout());
			panelSize.add(panelSizeTitle);
			panelSize.add(panelSizeSlider);
			panelSize.add(panelSizeLabel);
			
			/////////////////////////////////////////////
			//色コントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titleCol = new JLabel("色");
			JPanel panelColTitle = new JPanel();
			panelColTitle.setPreferredSize(new Dimension(30, 20));
			panelColTitle.add(titleCol);

			//数値
			labelCol = new JLabel("0");
			JPanel panelColLabel = new JPanel();
			panelColLabel.setPreferredSize(new Dimension(30, 20));
			panelColLabel.add(labelCol);
			
			//スライダー
			sliderCol = new JSlider(0, 200, 0);
			sliderCol.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderCol.getValue();
					messageToScratch = "sensor-update \"effect1\" " + value;
					sendMessage();
					labelCol.setText(String.valueOf(value));
					messageToScratch = "broadcast \"col\"";
					sendMessage();	
				}
			});
			JPanel panelColSlider = new JPanel();
			panelColSlider.add(sliderCol);
			
			//パネル作成
			JPanel panelCol = new JPanel();
			panelCol.setLayout(new FlowLayout());
			panelCol.add(panelColTitle);
			panelCol.add(panelColSlider);
			panelCol.add(panelColLabel);
			
			/////////////////////////////////////////////
			//魚眼レンズコントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titleLens = new JLabel("魚眼");
			JPanel panelLensTitle = new JPanel();
			panelLensTitle.setPreferredSize(new Dimension(30, 20));
			panelLensTitle.add(titleLens);

			//数値
			labelLens = new JLabel("0");
			JPanel panelLensLabel = new JPanel();
			panelLensLabel.setPreferredSize(new Dimension(30, 20));
			panelLensLabel.add(labelLens);
			
			//スライダー
			sliderLens = new JSlider(0, 999, 0);
			sliderLens.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderLens.getValue();
					messageToScratch = "sensor-update \"effect2\" " + value;
					sendMessage();
					labelLens.setText(String.valueOf(value));
					messageToScratch = "broadcast \"Lens\"";
					sendMessage();	
				}
			});
			JPanel panelLensSlider = new JPanel();
			panelLensSlider.add(sliderLens);
			
			//パネル作成
			JPanel panelLens = new JPanel();
			panelLens.setLayout(new FlowLayout());
			panelLens.add(panelLensTitle);
			panelLens.add(panelLensSlider);
			panelLens.add(panelLensLabel);
			
			/////////////////////////////////////////////
			//渦巻きレンズコントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titleTwi = new JLabel("渦巻");
			JPanel panelTwiTitle = new JPanel();
			panelTwiTitle.setPreferredSize(new Dimension(30, 20));
			panelTwiTitle.add(titleTwi);

			//数値
			labelTwi = new JLabel("0");
			JPanel panelTwiLabel = new JPanel();
			panelTwiLabel.setPreferredSize(new Dimension(30, 20));
			panelTwiLabel.add(labelTwi);
			
			//スライダー
			sliderTwi = new JSlider(0, 999, 0);
			sliderTwi.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderTwi.getValue();
					messageToScratch = "sensor-update \"effect3\" " + value;
					sendMessage();
					labelTwi.setText(String.valueOf(value));
					messageToScratch = "broadcast \"twist\"";
					sendMessage();	
				}
			});
			JPanel panelTwiSlider = new JPanel();
			panelTwiSlider.add(sliderTwi);
			
			//パネル作成
			JPanel panelTwi = new JPanel();
			panelTwi.setLayout(new FlowLayout());
			panelTwi.add(panelTwiTitle);
			panelTwi.add(panelTwiSlider);
			panelTwi.add(panelTwiLabel);
			
			/////////////////////////////////////////////
			//ピクセルレンズコントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titlePix = new JLabel("PIX");
			JPanel panelPixTitle = new JPanel();
			panelPixTitle.setPreferredSize(new Dimension(30, 20));
			panelPixTitle.add(titlePix);

			//数値
			labelPix = new JLabel("0");
			JPanel panelPixLabel = new JPanel();
			panelPixLabel.setPreferredSize(new Dimension(30, 20));
			panelPixLabel.add(labelPix);
			
			//スライダー
			sliderPix = new JSlider(0, 200, 0);
			sliderPix.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderPix.getValue();
					messageToScratch = "sensor-update \"effect4\" " + value;
					sendMessage();
					labelPix.setText(String.valueOf(value));
					messageToScratch = "broadcast \"pix\"";
					sendMessage();	
				}
			});
			JPanel panelPixSlider = new JPanel();
			panelPixSlider.add(sliderPix);
			
			//パネル作成
			JPanel panelPix = new JPanel();
			panelPix.setLayout(new FlowLayout());
			panelPix.add(panelPixTitle);
			panelPix.add(panelPixSlider);
			panelPix.add(panelPixLabel);
			
			/////////////////////////////////////////////
			//モザイクレンズコントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titleMos = new JLabel("モザ");
			JPanel panelMosTitle = new JPanel();
			panelMosTitle.setPreferredSize(new Dimension(30, 20));
			panelMosTitle.add(titleMos);

			//数値
			labelMos = new JLabel("0");
			JPanel panelMosLabel = new JPanel();
			panelMosLabel.setPreferredSize(new Dimension(30, 20));
			panelMosLabel.add(labelMos);
			
			//スライダー
			sliderMos = new JSlider(0, 50, 0);
			sliderMos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderMos.getValue();
					messageToScratch = "sensor-update \"effect5\" " + value;
					sendMessage();
					labelMos.setText(String.valueOf(value));
					messageToScratch = "broadcast \"mos\"";
					sendMessage();	
				}
			});
			JPanel panelMosSlider = new JPanel();
			panelMosSlider.add(sliderMos);
			
			//パネル作成
			JPanel panelMos = new JPanel();
			panelMos.setLayout(new FlowLayout());
			panelMos.add(panelMosTitle);
			panelMos.add(panelMosSlider);
			panelMos.add(panelMosLabel);
			
			/////////////////////////////////////////////
			//明るさレンズコントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titleLig = new JLabel("明");
			JPanel panelLigTitle = new JPanel();
			panelLigTitle.setPreferredSize(new Dimension(30, 20));
			panelLigTitle.add(titleLig);

			//数値
			labelLig = new JLabel("0");
			JPanel panelLigLabel = new JPanel();
			panelLigLabel.setPreferredSize(new Dimension(30, 20));
			panelLigLabel.add(labelLig);
			
			//スライダー
			sliderLig = new JSlider(0, 100, 0);
			sliderLig.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderLig.getValue();
					messageToScratch = "sensor-update \"effect6\" " + value;
					sendMessage();
					labelLig.setText(String.valueOf(value));
					messageToScratch = "broadcast \"lig\"";
					sendMessage();	
				}
			});
			JPanel panelLigSlider = new JPanel();
			panelLigSlider.add(sliderLig);
			
			//パネル作成
			JPanel panelLig = new JPanel();
			panelLig.setLayout(new FlowLayout());
			panelLig.add(panelLigTitle);
			panelLig.add(panelLigSlider);
			panelLig.add(panelLigLabel);
			
			/////////////////////////////////////////////
			//幽霊レンズコントローラー
			/////////////////////////////////////////////
			//タイトル
			JLabel titleGst = new JLabel("幽霊");
			JPanel panelGstTitle = new JPanel();
			panelGstTitle.setPreferredSize(new Dimension(30, 20));
			panelGstTitle.add(titleGst);

			//数値
			labelGst = new JLabel("0");
			JPanel panelGstLabel = new JPanel();
			panelGstLabel.setPreferredSize(new Dimension(30, 20));
			panelGstLabel.add(labelGst);
			
			//スライダー
			sliderGst = new JSlider(0, 100, 0);
			sliderGst.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent ae) {
					int value = (int)sliderGst.getValue();
					messageToScratch = "sensor-update \"effect7\" " + value;
					sendMessage();
					labelGst.setText(String.valueOf(value));
					messageToScratch = "broadcast \"gst\"";
					sendMessage();	
				}
			});
			JPanel panelGstSlider = new JPanel();
			panelGstSlider.add(sliderGst);
			
			//パネル作成
			JPanel panelGst = new JPanel();
			panelGst.setLayout(new FlowLayout());
			panelGst.add(panelGstTitle);
			panelGst.add(panelGstSlider);
			panelGst.add(panelGstLabel);
			
			/////////////////////////////////////////////
			//レイアウト作成
			/////////////////////////////////////////////
			getContentPane().setLayout(new GridLayout(9,1));
			getContentPane().add(panelSize);
			getContentPane().add(panelCol);
			getContentPane().add(panelLens);
			getContentPane().add(panelTwi);
			getContentPane().add(panelPix);
			getContentPane().add(panelMos);
			getContentPane().add(panelLig);
			getContentPane().add(panelGst);
			getContentPane().add(button01);
			
			setTitle("Scratch Effect Controller");
			setSize(300, 450);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		} catch(IOException e) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(400, 100);
			setLocationRelativeTo(null);
			setTitle("接続エラー");
			
			JLabel errMes = new JLabel("Scratchの外部センサー接続を有効にしてください。");
			setLayout(new FlowLayout());
			getContentPane().add(errMes);			
			setVisible(true);
		}
	}
	void sendMessage() {
		//先頭４バイト
		byte[] sizeBytes = {0, 0, 0, 0};
		sizeBytes[3] = (byte)(messageToScratch.length());
		for (int i=0; i<4; i++) {
			out.write(sizeBytes[i]);
		}
		out.print(messageToScratch);
	}
	void windowClosing(WindowEvent we) {
		try {
			out.close();
			socket.close();
		} catch(IOException e) {
			
		}
	}
	
	public static void main(String[] args) {
		Scratch01 myBody = new Scratch01();
		myBody.setVisible(true);
	}
}
