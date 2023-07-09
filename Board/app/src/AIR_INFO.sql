CREATE TABLE AIR_INFO
(
		ICAO_CODE	VARCHAR2(6),
		AIRPORT_NAME	VARCHAR2(10),
		TMFC		VARCHAR2(13),
		WD		VARCHAR2(4),
		WS		VARCHAR2(4),
		TA		VARCHAR2(4),
		QNH		VARCHAR2(5),
		CONSTRAINT AIR_INFO_PK PRIMARY KEY (ICAO_CODE, AIRPORT_NAME, TMFC)
);

COMMENT ON COLUMN AIR_INFO.ICAO_CODE IS 'ICAO 코드, 국내공항의 ICAO 코드';
COMMENT ON COLUMN AIR_INFO.AIRPORT_NAME IS '공항명';
COMMENT ON COLUMN AIR_INFO.TMFC IS '예보일시, 년월일시분(UTC) (yyyyMMddHHmm) - 발표일시로부터 +3시간까지 예보';
COMMENT ON COLUMN AIR_INFO.WD IS '바람의 풍향(°)';
COMMENT ON COLUMN AIR_INFO.WS IS '풍속(노트, kt)';
COMMENT ON COLUMN AIR_INFO.TA IS '지상 기온(℃)';
COMMENT ON COLUMN AIR_INFO.QNH IS '기압(0.01inHg)';

MERGE
 INTO USER2.AIR_INFO A
USING DUAL
   ON (A.TMFC = #{ICAOCode}) 
   AND (A.TMFC = #{AirPortName}) 
   AND (A.TMFC = #{TMFC})
 WHEN MATCHED THEN
 	  UPDATE
 	  	 SET A.TMFC = #{TMFC}
 WHEN NOT MATCHED THEN
      INSERT (
      	A.ICAO_CODE, 
      	A.AIRPORT_NAME, 
      	A.TMFC
      	A.WD
      	A.WS
      	A.TA
      	A.QNH
      )
      VALUES (
      	#{ICAOCode}, 
      	#{AirPortName},
      	#{TMFC},
      	#{WD},
      	#{WS},
      	#{TA},
      	#{QNH}
      )