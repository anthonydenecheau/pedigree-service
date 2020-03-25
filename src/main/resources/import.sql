CREATE TABLE IF NOT EXISTS POCJSONB (
	ID BIGINT NOT NULL,
	IDDOG INT NOT NULL,
	DATA JSONB,
	CONSTRAINT POCJSONB_PKY PRIMARY KEY (ID)
);

CREATE INDEX IF NOT EXISTS idxgin ON POCJSONB USING gin (DATA);

CREATE INDEX IF NOT EXISTS token_gin_idx ON POCJSONB USING gin ((data->'dog'->'tokens'->'number') jsonb_path_ops);

CREATE INDEX IF NOT EXISTS POCJSONB_IDDOG ON POCJSONB (IDDOG);

/*
 * 
 */

alter table pg_data alter column data type jsonb using data::JSON;

CREATE INDEX IF NOT EXISTS pg_data_id1 ON pg_data (iddog);

CREATE INDEX IF NOT EXISTS pg_data_id2 ON pg_data USING gin (data);

CREATE INDEX IF NOT EXISTS pg_data_id3 ON pg_data USING gin ((data->'dog'->'tokens'->'number') jsonb_path_ops);

CREATE INDEX IF NOT EXISTS pg_arbre_genealogie_id1 ON pg_arbre_genealogie (id_chien);

CREATE INDEX IF NOT EXISTS pg_arbre_genealogie_id2 ON pg_arbre_genealogie (id_pere);

CREATE INDEX IF NOT EXISTS pg_arbre_genealogie_id3 ON pg_arbre_genealogie (id_mere);
