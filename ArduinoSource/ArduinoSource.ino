
int led = 13;

void setup()
{
  // start serial port at 9600 bps:
  Serial.begin(9600);
  pinMode(led, OUTPUT); 

}

void loop()
{
  /*
  //--- Lese fra java server, skru av/på led ---//
  
  if(Serial.available() > 0) {
    char b = Serial.read();
    if(b == 1){
      digitalWrite(led, HIGH);
    }else if(b == 0){
      digitalWrite(led, LOW);  
    }  
  }
  
  /*
  //--- Lese fra lyssensor og sende til java server ---//
  
  Serial.write(map(analogRead(A0), 0,1024, 0, 255));
  delay(100);
  */
}


