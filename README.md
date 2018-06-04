ASRADA PROJECT
=======

## Outline


server
======


----------
portable_server
===============

Cluster
-------



IVI
---
Google Maps API

> In order to run this application on your phone you'll first need to
> get an API key.  Instructions on how to setup your API key can be
> found in the [  blog](https://yumdevelop.blogspot.kr/2018/04/android-googlemaps-api.html).

	 

----------
car
===

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


----------

portable_client
===============
	
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



