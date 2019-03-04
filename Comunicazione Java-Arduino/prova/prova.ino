byte dato = 0;

void setup()
{
  pinMode(13, OUTPUT);
  Serial.begin(9600);
}

void loop()
{
  if (Serial.available())
  {
    dato = Serial.read();
    if (dato == 'a')
    {
      digitalWrite(13, HIGH);
      delay(1000);
    }
    else
    {
      digitalWrite(13, LOW);
    }
  }

}
