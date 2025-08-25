#define SENSOR_1 A0 // Sensor de temp conectado ao A0

double TEMP; // Temperatura medida pelo sensor

void setup()
{
  Serial.begin(9600); // Velocidade de transmissão para PC
  pinMode(SENSOR_1, INPUT);
}

void loop()
{
  TEMP = digitalRead(PORTA_ANALOG_0);
  delay(200);
}