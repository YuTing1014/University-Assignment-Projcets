/*19000505 19000541 19000437 SIDP DCN
* 
* EDB4703: Systems Integration Design Project MAY 2023
* EDB3023/EEB4063: Data and Computer Network MAY 2023
* 26th July 2023
* 
* GROUP 3 
* 
* PROJECT: Pflege Old Folks Health Monitor Wristband
* 1	Tan Yu Ting	19000505	
* 2	Nur Amira binti Ahmad Rezal	19000541	
* 3	Hana Najwa Binti Abdul Aziz	19000437	
* 
* Submitted to:
* Dr. Fawnizu Azmadi B Hussin
* Ir. Dr. Huzein Fahmi B Hawari
* 
*/

#include <Wire.h>
#include <Adafruit_MLX90614.h>
#include <MPU6050.h>
#include <stdio.h>
#include <string.h>
#include "WiFi.h"
#include "eloquent.h"
#include "eloquent/networking/wifi/WifiScanner.h"
#define THINGER_SERIAL_DEBUG

#include <ThingerESP32.h>
#define USERNAME "amirahmd"
#define DEVICE_ID "IOT_PFLEGE"
#define DEVICE_CREDENTIAL "V52QON0JAzTaserG"

const char* SSID = "DunMindYou";
const char* PASSWORD= "00009999";

//Pulse Sensor Variable
const int pulsePin = 35;           // Pulse Sensor output pin
const int numSamples = 250;        // Number of samples to average
int pulseValue;                    // Raw pulse sensor value
unsigned long previousMillis = 0;  // Previous time in milliseconds
unsigned long interval = 1000;     // Update interval for BPM calculation

ThingerESP32 thing(USERNAME, DEVICE_ID, DEVICE_CREDENTIAL);

//MLX90614 sensor Variable
Adafruit_MLX90614 mlx = Adafruit_MLX90614();
const float feverThreshold = 36.5;  // Celsius

//MPU6050 Accelerometer Variable
MPU6050 mpu;
const float alpha = 1.0;             // Complementary filter parameter (adjust as needed)
const float thresholdFall = 0.9;     // Threshold for fall detection (adjust as needed)
const float thresholdSitting = 1.5;  // Threshold for sitting detection (adjust as needed)
//const float thresholdWalking = 2.0;  // Threshold for walking detection (adjust as needed)
const float thresholdRunning = 2.0;  // Threshold for running detection (adjust as needed)
float lastAccelMagnitude = 0.0;      // Previous magnitude of acceleration vector

//Buzzer Variable
#define BUZZER_PIN 13 // Change this to the GPIO pin connected to the buzzer

class WiFiIndoorPositioning {
public:
  /**
         * Get current room id
         */
  template<typename Scanner>
  int predictRoomId(Scanner& scanner) {
    float x[15] = { 0 };
    uint16_t votes[3] = { 0 };

    scanner.scan();
    convertToFeatures(scanner, x);

    if (x[9] <= -80.5) {
      if (x[14] <= -89.5) {
        votes[2] += 1;
      } else {
        if (x[13] <= -73.0) {
          if (x[2] <= -41.0) {
            if (x[7] <= -60.5) {
              if (x[13] <= -75.5) {
                votes[1] += 1;
              } else {
                votes[2] += 1;
              }
            } else {
              votes[0] += 1;
            }
          } else {
            votes[2] += 1;
          }
        } else {
          votes[2] += 1;
        }
      }
    } else {
      if (x[13] <= -71.0) {
        if (x[9] <= -75.5) {
          votes[1] += 1;
        } else {
          if (x[12] <= -76.5) {
            votes[0] += 1;
          } else {
            votes[1] += 1;
          }
        }
      } else {
        votes[2] += 1;
      }
    }
    if (x[6] <= -42.5) {
      votes[1] += 1;
    } else {
      if (x[9] <= -80.5) {
        if (x[2] <= -41.0) {
          if (x[12] <= -72.5) {
            if (x[9] <= -84.0) {
              if (x[2] <= -90.0) {
                votes[1] += 1;
              } else {
                if (x[14] <= -44.0) {
                  votes[0] += 1;
                } else {
                  if (x[2] <= -88.5) {
                    votes[0] += 1;
                  } else {
                    votes[1] += 1;
                  }
                }
              }
            } else {
              votes[0] += 1;
            }
          } else {
            votes[2] += 1;
          }
        } else {
          votes[2] += 1;
        }
      } else {
        if (x[14] <= -42.0) {
          if (x[10] <= -69.5) {
            votes[2] += 1;
          } else {
            votes[1] += 1;
          }
        } else {
          if (x[2] <= -90.0) {
            votes[1] += 1;
          } else {
            votes[0] += 1;
          }
        }
      }
    }
    if (x[13] <= -73.5) {
      if (x[2] <= -41.0) {
        if (x[0] <= -45.0) {
          votes[1] += 1;
        } else {
          if (x[5] <= -43.5) {
            votes[1] += 1;
          } else {
            if (x[13] <= -78.5) {
              if (x[1] <= -46.5) {
                votes[1] += 1;
              } else {
                if (x[13] <= -86.5) {
                  votes[0] += 1;
                } else {
                  if (x[9] <= -82.5) {
                    if (x[9] <= -85.5) {
                      votes[1] += 1;
                    } else {
                      if (x[10] <= -69.0) {
                        votes[0] += 1;
                      } else {
                        votes[1] += 1;
                      }
                    }
                  } else {
                    votes[1] += 1;
                  }
                }
              }
            } else {
              if (x[12] <= -71.0) {
                votes[0] += 1;
              } else {
                votes[2] += 1;
              }
            }
          }
        }
      } else {
        votes[2] += 1;
      }
    } else {
      votes[2] += 1;
    }
    if (x[9] <= -79.5) {
      if (x[12] <= -73.5) {
        if (x[9] <= -84.5) {
          if (x[13] <= -84.5) {
            if (x[2] <= -88.0) {
              votes[1] += 1;
            } else {
              votes[0] += 1;
            }
          } else {
            votes[1] += 1;
          }
        } else {
          if (x[2] <= -86.5) {
            votes[1] += 1;
          } else {
            if (x[9] <= -80.5) {
              votes[0] += 1;
            } else {
              votes[1] += 1;
            }
          }
        }
      } else {
        if (x[10] <= -75.5) {
          votes[2] += 1;
        } else {
          if (x[12] <= -70.0) {
            votes[0] += 1;
          } else {
            votes[2] += 1;
          }
        }
      }
    } else {
      if (x[7] <= -55.0) {
        votes[1] += 1;
      } else {
        votes[0] += 1;
      }
    }
    if (x[13] <= -73.5) {
      if (x[12] <= -72.5) {
        if (x[7] <= -55.0) {
          if (x[13] <= -77.5) {
            votes[1] += 1;
          } else {
            if (x[7] <= -60.5) {
              votes[1] += 1;
            } else {
              votes[0] += 1;
            }
          }
        } else {
          votes[0] += 1;
        }
      } else {
        if (x[3] <= -43.5) {
          votes[2] += 1;
        } else {
          if (x[14] <= -85.0) {
            votes[2] += 1;
          } else {
            if (x[12] <= -68.5) {
              votes[0] += 1;
            } else {
              votes[2] += 1;
            }
          }
        }
      }
    } else {
      if (x[12] <= -76.5) {
        votes[1] += 1;
      } else {
        votes[2] += 1;
      }
    }
    if (x[7] <= -53.5) {
      if (x[13] <= -74.0) {
        if (x[13] <= -82.5) {
          votes[1] += 1;
        } else {
          if (x[14] <= -86.5) {
            if (x[12] <= -73.5) {
              votes[0] += 1;
            } else {
              votes[2] += 1;
            }
          } else {
            if (x[10] <= -67.5) {
              if (x[2] <= -89.0) {
                votes[2] += 1;
              } else {
                votes[0] += 1;
              }
            } else {
              votes[1] += 1;
            }
          }
        }
      } else {
        votes[2] += 1;
      }
    } else {
      if (x[12] <= -73.5) {
        votes[0] += 1;
      } else {
        if (x[10] <= -76.5) {
          votes[2] += 1;
        } else {
          if (x[14] <= -41.5) {
            votes[0] += 1;
          } else {
            votes[2] += 1;
          }
        }
      }
    }
    if (x[10] <= -67.0) {
      if (x[2] <= -41.5) {
        if (x[12] <= -74.5) {
          votes[0] += 1;
        } else {
          if (x[13] <= -78.5) {
            votes[0] += 1;
          } else {
            votes[2] += 1;
          }
        }
      } else {
        if (x[7] <= -53.5) {
          votes[2] += 1;
        } else {
          if (x[12] <= -69.5) {
            if (x[14] <= -86.0) {
              votes[2] += 1;
            } else {
              votes[0] += 1;
            }
          } else {
            votes[2] += 1;
          }
        }
      }
    } else {
      if (x[12] <= -69.0) {
        votes[1] += 1;
      } else {
        votes[2] += 1;
      }
    }
    if (x[14] <= -90.5) {
      if (x[7] <= -59.0) {
        votes[1] += 1;
      } else {
        votes[2] += 1;
      }
    } else {
      if (x[13] <= -73.5) {
        if (x[2] <= -87.0) {
          if (x[7] <= -55.5) {
            if (x[6] <= -44.5) {
              votes[1] += 1;
            } else {
              if (x[14] <= -89.5) {
                votes[0] += 1;
              } else {
                if (x[7] <= -65.5) {
                  if (x[10] <= -76.0) {
                    votes[2] += 1;
                  } else {
                    votes[1] += 1;
                  }
                } else {
                  votes[1] += 1;
                }
              }
            }
          } else {
            votes[0] += 1;
          }
        } else {
          if (x[6] <= -42.5) {
            votes[1] += 1;
          } else {
            if (x[5] <= -43.5) {
              votes[1] += 1;
            } else {
              if (x[9] <= -85.5) {
                if (x[12] <= -75.5) {
                  votes[1] += 1;
                } else {
                  votes[2] += 1;
                }
              } else {
                votes[0] += 1;
              }
            }
          }
        }
      } else {
        if (x[9] <= -79.0) {
          votes[2] += 1;
        } else {
          votes[1] += 1;
        }
      }
    }
    if (x[10] <= -71.5) {
      if (x[2] <= -41.0) {
        if (x[12] <= -74.5) {
          votes[0] += 1;
        } else {
          votes[2] += 1;
        }
      } else {
        votes[2] += 1;
      }
    } else {
      if (x[10] <= -68.5) {
        if (x[2] <= -87.0) {
          if (x[2] <= -89.5) {
            votes[0] += 1;
          } else {
            votes[1] += 1;
          }
        } else {
          votes[0] += 1;
        }
      } else {
        if (x[12] <= -70.0) {
          votes[1] += 1;
        } else {
          votes[2] += 1;
        }
      }
    }
    if (x[2] <= -41.0) {
      if (x[10] <= -71.0) {
        if (x[13] <= -73.0) {
          votes[0] += 1;
        } else {
          votes[2] += 1;
        }
      } else {
        votes[1] += 1;
      }
    } else {
      votes[2] += 1;
    }


    // return argmax of votes
    uint8_t classIdx = 0;
    float maxVotes = votes[0];

    for (uint8_t i = 1; i < 3; i++) {
      if (votes[i] > maxVotes) {
        classIdx = i;
        maxVotes = votes[i];
      }
    }

    return (lastRoomId = classIdx);
  }

  /**
         * Get current room name
         */
  template<typename Scanner>
  String predictRoomName(Scanner& scanner) {
    uint8_t roomId = predictRoomId(scanner);

    switch (roomId) {

      case 0: return (lastRoomName = "kitchen");

      case 1: return (lastRoomName = "room");

      case 2: return (lastRoomName = "toilet");

      default: return (lastRoomName = "???");
    }
  }

  /**
         * Test if current location is the given one by id
         */
  bool isIn(uint8_t roomId) {
    return roomId == lastRoomId;
  }

  /**
         * Test if current location is the given one by name
         */
  bool isIn(String roomName) {
    return roomName == lastRoomName;
  }


protected:
  uint8_t lastRoomId = 255;
  String lastRoomName;

  template<typename Scanner>
  void convertToFeatures(Scanner& scanner, float* x) {
    while (scanner.hasNext()) {
      String ssid = scanner.ssid();
      float rssi = scanner.rssi();

      scanner.next();


      if (ssid == "DISPLAY-03") {
        x[0] = rssi;
        continue;
      }

      if (ssid == "DISPLAY-01B") {
        x[1] = rssi;
        continue;
      }

      if (ssid == "LEARNING HUB 05") {
        x[2] = rssi;
        continue;
      }

      if (ssid == "GeniusPOS") {
        x[3] = rssi;
        continue;
      }

      if (ssid == "Galaxy A023651") {
        x[4] = rssi;
        continue;
      }

      if (ssid == "LEARNING HUB 46") {
        x[5] = rssi;
        continue;
      }

      if (ssid == "DIRECT-b6-HP M280 LaserJet") {
        x[6] = rssi;
        continue;
      }

      if (ssid == "TP-LINK_9118") {
        x[7] = rssi;
        continue;
      }

      if (ssid == "DIRECT-ef-HP M281 LaserJet") {
        x[8] = rssi;
        continue;
      }

      if (ssid == "EnergisingFutures") {
        x[9] = rssi;
        continue;
      }

      if (ssid == "NasiKandaq-2.4Ghz") {
        x[10] = rssi;
        continue;
      }

      if (ssid == "CERDAS") {
        x[11] = rssi;
        continue;
      }

      if (ssid == "AnchorAP1") {
        x[12] = rssi;
        continue;
      }

      if (ssid == "AnchorAP3") {
        x[13] = rssi;
        continue;
      }

      if (ssid == "TP-Link_CD36") {
        x[14] = rssi;
        continue;
      }
    }
  }
};

WiFiIndoorPositioning indoor;
String curr_loc="Current location: ";
String curr_pos="Patient is currently in ";

void setup() {
  Wire.begin();
  Serial.begin(115200);

  pinMode(pulsePin, INPUT);
  mlx.begin();
  mpu.initialize();
  pinMode(BUZZER_PIN, OUTPUT);

  WiFi.begin(SSID, PASSWORD);

  while (WiFi.status() != WL_CONNECTED) {
    //delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");

  thing["Temperature"] >> [](pson& out){
      out = temperature_display();
  };
  
  thing["Temperature Status"] >> [](pson& out){
      out =   temperature_status_display();
  };
  
  thing["Pulse"] >> [](pson& out){
      out = pulse_value();
  };

  thing["Positioning"] >> [](pson& out){
      out = patient_pos();
  };


  thing["Indoor Positioning"] >> [](pson& out){
      out = patient_loc();
  };

}

int pulse_value(){
  int bpm;
  pulseValue = analogRead(pulsePin);  // Read raw pulse sensor value
  Serial.print("Pulse Value : ");
  Serial.println(pulseValue);
  unsigned long currentMillis = millis();  // Current time in milliseconds
  if (pulseValue < 2000) { pulseValue = 0; }
  // Calculate BPM every interval
  if (currentMillis - previousMillis >= interval) {
    bpm = map(pulseValue, 2000, 4032, 70, 150);  // Map pulseValue to BPM range (60-100)
    // Print the BPM value
    if (bpm < 0) { bpm = 0; }
    Serial.println(bpm);

    previousMillis = currentMillis;  // Update previousMillis
  }

  return bpm;

}

void loop() {
    thing.handle();
}

String temperature_status_display(){
  // Read the object temperature (the temperature of the body)
  float objectTemp = mlx.readObjectTempC();
  String fever= "Fever detected!";
  String normal= "Normal body temperature.";

  // Check if the body temperature exceeds the fever threshold
  if (objectTemp >= feverThreshold) {
    Serial.println(fever);
    return fever;
    // Add further actions here, e.g., alerting the user, activating an alarm, etc.
  }  
  else{
    Serial.println(normal);
    return normal;
  }
}

float temperature_display(){
  // Read the object temperature (the temperature of the body)
  float objectTemp = mlx.readObjectTempC();
  Serial.print("Body Temperature: "); 
  Serial.print(objectTemp);
  Serial.println(" °C");

  return objectTemp;
}
// String Temp_status(){
//   Serial.println("Fever detected!");
// }
String patient_pos(){
  int16_t accelX, accelY, accelZ;
  int16_t gyroX, gyroY, gyroZ;
  mpu.getMotion6(&accelX, &accelY, &accelZ, &gyroX, &gyroY, &gyroZ);

  String fall = "Fall detected!"; 
  String sit = "Sitting/Walking detected.";
  String run = "Running detected."; 

  // Convert raw accelerometer values to float
  float floatAccelX = accelX / 16384.0;
  float floatAccelY = accelY / 16384.0;
  float floatAccelZ = accelZ / 16384.0;
  
  // Apply a complementary filter to get the gravity component
  float accelMagnitude = sqrt(pow(floatAccelX, 2) + pow(floatAccelY, 2) + pow(floatAccelZ, 2));
  accelMagnitude = alpha * accelMagnitude + (1 - alpha) * lastAccelMagnitude;
  Serial.print("Value accel ");
  Serial.println(accelMagnitude);
  lastAccelMagnitude = accelMagnitude;

  // Detect different activities based on the magnitude of the acceleration vector
  if (accelMagnitude < thresholdFall) {
    Serial.println(fall);
    digitalWrite(BUZZER_PIN, HIGH);
    return fall;
    // Take appropriate actions when a fall is detected
    // For example, send an alert or trigger an action
  } else if (accelMagnitude < thresholdSitting) {
    Serial.println(sit);
    return sit;
    // Take appropriate actions for sitting
  }else if (accelMagnitude < thresholdRunning) {
    Serial.println(run);
    return run;
    // Take appropriate actions for running
  }
  //delay(1000);  // Adjust the delay according to your requirements
}

String patient_loc(){

  Serial.print(curr_loc);
  Serial.println(indoor.predictRoomName(wifiScanner));

  String position= curr_loc + indoor.predictRoomName(wifiScanner);

    // customize as per your needs
  return position;
  //delay(1000);
}