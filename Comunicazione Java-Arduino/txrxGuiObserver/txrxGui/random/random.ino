float umi=50.0;
float temp=20.0; 
int caso;
char data;
bool riscaldamento=false;
void setup(){
  Serial.begin(9600);
  pinMode(13, OUTPUT);
}
 
void loop(){
  if(Serial.available() > 0){
    data = Serial.read();        //Legge il dato in arrivo e lo mette in data
    
    if(data == '1'){             //Se il dato ricevuto è 1, allora...
      digitalWrite(13, HIGH);       //...accendi il led e...
      riscaldamento=true;
    }else if(data == '0'){       // Altrimenti se è 0 ...
      digitalWrite(13, LOW);        //...spegni il led e...
      riscaldamento=false;
    }
  }
  caso=random(100);
  if ((caso>66)&&(temp<100))
  temp=temp+float(caso)/100;
  if ((caso<34)&&(temp>0)&&(riscaldamento==false))
  temp=temp-float(200-caso)/100;
  caso=random(100);
  if ((caso>66)&&(umi<100))
  umi=umi+float(caso)/100;
  if ((caso<34)&&(umi>0))
  umi=umi-float(100-caso)/100;
  Serial.println(String(temp)+"-"+String(umi));
 
  delay(1000);
}
