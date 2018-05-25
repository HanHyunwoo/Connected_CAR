ASRADA PROJECT
=======

## Outline

 - **server**

- **portable_server**
	 - Android Application : cluster, IVI
	 

    
    
car
---
	To portable_client
	
	send value     
	:W28 00000000 0000000000000000 53 \r      
    +--------------------------------------------------+
    | sensor         | id  |          value            |
    +--------------------------------------------------|
    | accel          |  1  |            ?              |
    | break          |  2  |            ?              |
    | safety_dis     |  3  |           0-1             |
    | snooze         |  4  |           0-1             |
    +--------------------------------------------------+

	receive value     
	:U28 00000000 0000000000000000 53 \r      
    +--------------------------------------------------+
    | sensor         | id  |          value            |
    +--------------------------------------------------|
    | motor          |  5  |           0-1             |
    | led            |  6  |           0-1             |
    +--------------------------------------------------+

portable_client
---------------
	
	To car
	send value     
	:W28 00000000 0000000000000000 53 \r      
    +--------------------------------------------------+
    | sensor         | id  |          value            |
    +--------------------------------------------------|
    | motor          |  5  |           0-1             |
    | led            |  6  |           0-1             |
    +--------------------------------------------------+

	To portable_server 

    cluster (per second)
    +--------------------------------------------------+
    | value                                            |
    +--------------------------------------------------|
    | velocity                                         |
    | rpm (= accel)                                    |
    | spare_battery                                    |
    +--------------------------------------------------+

    log (per second)
    +--------------------------------------------------+
    | value                                            |
    +--------------------------------------------------|
    | car_id                                           |
    | date(yyyy-MM-dd HH:mm:ss)                        |
    | accel                                            |
    | break                                            |
    | safety_dis                                       |
    | snooze                                           |
    | velocity                                         |
    +--------------------------------------------------+



