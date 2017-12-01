package codigo;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import acmx.export.javax.swing.JButton;


/*
 * Autor: Alberto Goujon Guti�rrez
 * 
 * El Arkanoid pero orientado a objetos
 * En esta clase se ejecuta el conjuto de las clases del proyecto para que funcione el juego
 * 
 */

public class Arkanoid extends acm.program.GraphicsProgram{

	//Integers de pelota, barra-----------------------------------------------------------------------------------------------
		int anchoLadrillo = 25;																		//Ancho del ladrillo
		int altoLadrillo = 15;																		//Alto del ladrillo
		int largoBarra= 40;																			//Largo barra
		int anchoBarra= 7;
		int tama�oPelota = 7;
	//------------------------------------------------------------------------------------------------------------------------
		
	//Especificaciones de tama�o de los diferentes objetos--------------------------------------------------------------------									
		Pelota pelota1 = new Pelota(tama�oPelota, Color.GREEN);										//Pelota	
		Barra barra1 = new Barra(largoBarra, anchoBarra, Color.BLACK);								//Barra
		Bonus bonus1 = new Bonus(25,15,Color.WHITE);
		//GObject Heart = new GImage("ACM_Arkanoid/img/Heart.png", 21, 21);							//Imagen coraz�n
		//JButton salir = new JButton("EXIT!");														//Salir
	//------------------------------------------------------------------------------------------------------------------------
	
	//Para las variables random-----------------------------------------------------------------------------------------------
		RandomGenerator random = new RandomGenerator();												//Random	
	//------------------------------------------------------------------------------------------------------------------------
	
	//Menu del arkanoid-------------------------------------------------------------------------------------------------------
		Marcador marcador = new Marcador(100, 40);													//Caja del marcador
		Vidas vidaAbajo = new Vidas (50,40);														//Caja de las vidas
		Niveles levelUp = new Niveles (50, 40);
		int espacioMenu = 130;																		//Barra del men�
		
	//------------------------------------------------------------------------------------------------------------------------
	
	//Finales del juego-------------------------------------------------------------------------------------------------------
		GLabel hasPerdido = new GLabel("GAME OVER");												//Texto de has perdido
		GLabel hasGanado = new GLabel("YOU WIN!");													//Texto de has ganado
		
	//------------------------------------------------------------------------------------------------------------------------
		
	public void init(){
		
		addMouseListeners();
		setSize(550, 600);																			//Tama�o de la pantalla
		GRect lateral = new GRect(3, getWidth());													//L�nea del men�
		lateral.setFilled(true);																	//L�nea del men�
		add(lateral, getWidth()- espacioMenu - lateral.getWidth()- pelota1.getWidth(), 0);			//Lo a�ade a la pantalla la l�nea del men�
		add(pelota1, 50, (int)(getHeight()*0.60 - pelota1.getHeight()));							//A�ade a la pelota en la pantalla
		add(barra1, 0 , getHeight()*0.80);															//A�ade la barra del juego
		setBackground(Color.GRAY);
	}
	
	public void run()
	{
		dibujaNivel01();
		dibujaNivel011();																			//Fila de ladrillos
		dibujaNivel012();																			//Fila de ladrillos m�s abajo
		waitForClick();																				//Click para empezar el juego
		marcador.dibuja(this);																		//A�ade el marcador en la pantalla
		vidaAbajo.dibuja(this);																		//A�ade las vidas en la pantalla
		levelUp.dibuja(this);																		//A�ade el n�mero del nivel en pantalla
		actualizaNivel();																			//Actaulizara el nivel
		
	}

	public void mouseMoved (MouseEvent evento)														//Condici�n que la barra hace para que no se pase del espacio del men� y se mueva
	{
		barra1.mueveBarra(evento.getX(), getWidth()- espacioMenu);
	}
	
	private void dibujaNivel01()																	//Nivel de l�neas de ladrillos
	{
		int numFilas = 7;
		int numColumnas = 12;
		for (int i=0; i < numFilas; i++)
		{
			for(int j=0; j < numColumnas; j++)
			{
				Ladrillo miLadrillo =
						new Ladrillo(j * anchoLadrillo + 50, i * altoLadrillo + 60, anchoLadrillo, altoLadrillo,
								Color.CYAN);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void dibujaNivel011()																	//Linea de bloques 
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<12; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i + 50 , altoLadrillo + 90, anchoLadrillo , altoLadrillo, Color.YELLOW);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void dibujaNivel012()																	//Lineas de bloques 
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<12; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i + 50 , altoLadrillo + 60, anchoLadrillo , altoLadrillo, Color.YELLOW);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void dibujaNivel02()																	//Nive2 pir�mide																		
	{
		int numLadrillos = 14; 	
		for (int j=0; j < numLadrillos; j++)
		{
			for(int i=j; i < numLadrillos; i++)
			{
				Ladrillo miLadrillo =
						new Ladrillo((anchoLadrillo* i - anchoLadrillo*j/2 + ((getWidth()- 140) - anchoLadrillo * numLadrillos)/2),
								altoLadrillo*j,
								anchoLadrillo, 
								altoLadrillo, 
								Color.CYAN);
				if (random.nextInt(numLadrillos)==5)
				{
					add(bonus1, anchoLadrillo* i-3 - anchoLadrillo*j/2,altoLadrillo*j);
				}

				add(miLadrillo);
				pause(7);
			}
		}
	}	
	
	private void dibujaNivel021()																	//Segundo nivel de ladrillo = 14 
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<14; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i+22, altoLadrillo*j/2, anchoLadrillo, altoLadrillo, Color.YELLOW);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void dibujaNivel022()																	//Segundo nivel de ladrillo 2 = 13
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<13; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i + 35 , altoLadrillo, anchoLadrillo, altoLadrillo, Color.YELLOW);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void dibujaNivel03()																	//Nive3 mitad  de pir�mide																		
	{
		int numLadrillos = 14; 	
		for (int j=0; j < numLadrillos; j++)
		{
			for(int i=j; i < numLadrillos; i++)
			{
				Ladrillo miLadrillo =
						new Ladrillo((anchoLadrillo* i - anchoLadrillo*j + 
								((getWidth() -180) - anchoLadrillo * numLadrillos)/2),
								altoLadrillo*j,
								anchoLadrillo, 
								altoLadrillo, 
								Color.CYAN);
				if (random.nextInt(numLadrillos)==5)
				{
					add(bonus1, anchoLadrillo* i - anchoLadrillo*j,altoLadrillo*j);
				}
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void dibujaNivel031()																	//Segundo nivel de ladrillo = 13 
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<13; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i+2, altoLadrillo, anchoLadrillo, altoLadrillo, Color.YELLOW);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	
	private void actualizaNivel()																	//Condici�n para que pase entre niveles  a parte de que el juego funcien a partir de vidas
	{
		while (vidaAbajo.numvidas >=1 && vidaAbajo.numvidas <= 3)									//hace que funcione en funcion del n�mero de vidas
		{
			pelota1.muevete(this);																	//hace que la pelota se mueva
			barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);						//truco para seguir a  la pelota
			float tiempoProgresivo = 5;																//para hacer un rebote progresivo
			tiempoProgresivo -= 0.000333;															//resta al 5 cada segundo milesimas de velocidad de movimiento	
			pause(0);
			
				if(tiempoProgresivo<=1)
				{
					tiempoProgresivo= 1;
				}
			//ENLACE ENTRE NIVELE 1 AL 2-------------------------------------------------------------------------------------------
			
			if(marcador.puntuacion >= 2160)
			{
				setBackground(Color.MAGENTA);
				dibujaNivel02();
				dibujaNivel021();
				dibujaNivel022();
				levelUp.actualizaMarcadorNivel(+1);
				waitForClick();
				pelota1.setLocation( 50, getHeight()*0.70 - pelota1.getHeight());
				while (vidaAbajo.numvidas >=1 && vidaAbajo.numvidas <= 3)							//hace que funcione en funcion del n�mero de vidas
				{
					pelota1.muevete(this);															//hace que la pelota se mueva
					barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);				//truco para seguir a  la pelota
					pause(0);					
				//ENLACE ENTRE EL NIVEL 2 AL 3-----------------------------------------------------------------------------------------
					if(marcador.puntuacion >= 4800)
					{
						setBackground(Color.ORANGE);
						dibujaNivel03();
						dibujaNivel031();
						levelUp.actualizaMarcadorNivel(-1);
						waitForClick();
						pelota1.setLocation( 50, getHeight()*0.70 - pelota1.getHeight());
						while (vidaAbajo.numvidas >=1 && vidaAbajo.numvidas <= 3)					//hace que funcione en funcion del n�mero de vidas
						{
							pelota1.muevete(this);													//hace que la pelota se mueva
							barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);		//truco para seguir a  la pelota
							pause(0);
				//FINAL DEL JUEGO Y A�ADE hasGanado-------------------------------------------------------------------------------------
							if(marcador.puntuacion >= 7160)
							{
								pelota1.setLocation( 50, getHeight()*0.70 - pelota1.getHeight()); 	//Recoloco la pelota para que no se mueva
								remove(pelota1);													//La elimino
								remove(barra1);														//Elimino la barra
								add(hasGanado, getWidth()/3.5, getHeight()/2.5);					//A�ado el YOU WIN!
							}
				//-----------------------------------------------------------------------------------------------------------------------
						}
						
					}
				}				
			}
			
			//Si el juego tiene 0 vidas termina el juego a�adiendo:-----------------------------------------------------------------------
			if(vidaAbajo.numvidas <= 0)																
			{
				remove(pelota1);																	//Elimina la pelota
				remove(barra1);																		//Elimina la barra
				add(hasPerdido, getWidth()/3.5, getHeight()/1.5);									//A�ado GAME OVER
			}
			//-----------------------------------------------------------------------------------------------------------------------------
		}
	}

}












