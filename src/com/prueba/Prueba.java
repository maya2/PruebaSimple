package com.prueba;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.JTextField;

public class Prueba extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField texto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Prueba dialog = new Prueba();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Prueba() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			texto = new JTextField();
			contentPanel.add(texto);
			texto.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JasperReport reporte;
				        JasperPrint jasperPrint;
						try {
							
							Map<String, Object> parametros = new HashMap<String, Object>();
							
							
							parametros.put("texto", texto.getText());
							
					       	
							reporte = (JasperReport) JRLoader.loadObjectFromFile("prueba1.jasper");
					        
							jasperPrint = JasperFillManager.fillReport(reporte, parametros);
							
							JDialog viewer = new JDialog(new JFrame(),"Vista previa", true);
							
							viewer.setSize(viewer.getToolkit().getScreenSize());    
							viewer.setSize(900,600);
							viewer.setLocationRelativeTo(null);
							JRViewer jrv = new JRViewer(jasperPrint);
							viewer.getContentPane().add(jrv);
							viewer.setVisible(true);
							
						} catch (JRException e) {
							e.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
