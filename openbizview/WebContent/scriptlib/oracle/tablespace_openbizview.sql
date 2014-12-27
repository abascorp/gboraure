prompt Creando el TableSpace default: <dbopennomina>
prompt Introduzca el RUTA\NOMBRE del DataFile EJEMPLO: /u01/app/oracle/oradata/XE/dbbizview01.DBF
create tablespace bdbizview 
       datafile '&1'    
       size 1000M
default storage (initial 256K
                 next 256k
                 pctincrease   0);
prompt Creando el TableSpace default: <opennominaidx>
prompt Introduzca el RUTA\NOMBRE del DataFile EJEMPLO: /u01/app/oracle/oradata/XE/dbbizviewidx01.DBF
create tablespace bizviewidx 
       datafile '&1'    
       size 300M
default storage (initial 256K
                 next 256k
                 pctincrease   0)
/
