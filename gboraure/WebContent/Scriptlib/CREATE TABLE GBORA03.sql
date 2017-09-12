
  CREATE TABLE "OPENBIZVIEW"."GBORA03" 
   (	"CODGRA" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"CODSEC" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"CODLOT" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"DESLOT" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
	"USRCRA" VARCHAR2(50 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(50 BYTE), 
	"FECACT" DATE, 
	 CONSTRAINT "GBORA03_PK" PRIMARY KEY ("CODGRA", "CODSEC", "CODLOT")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "GBORA03_FK1" FOREIGN KEY ("CODGRA", "CODSEC")
	  REFERENCES "OPENBIZVIEW"."GBORA02" ("CODGRA", "CODSEC") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."CODGRA" IS 'CODIGO GRANJA';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."CODSEC" IS 'CODIGO DE SECTOR';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."CODLOT" IS 'CODIGO DE LOTE';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."DESLOT" IS 'DESCRIPCION DE LOTE';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."USRCRA" IS 'USUARIO DE CREACION DEL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."FECCRE" IS 'FECHA DE CREACION DEL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."USRACT" IS 'USUARIO QUE ACTUALIZO EL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA03"."FECACT" IS 'FECHA DE ACTUALIZACION DEL REGISTRO';
