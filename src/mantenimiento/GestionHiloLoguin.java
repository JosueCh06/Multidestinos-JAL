package mantenimiento;

import vista.Login;

public class GestionHiloLoguin extends Thread {
	
	public void run(){
		Login.lblElBotonSe.setVisible(true);
		Login.lbls.setVisible(true);
		for(int i = 60; i >= 0; i--){
			Login.lbls.setText(i+"s");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Login.lblElBotonSe.setVisible(false);
		Login.lbls.setVisible(false);
		Login.lbls.setText("60S");
		Login.btnIngresar.setEnabled(true);
	}
}
