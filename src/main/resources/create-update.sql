CREATE TABLE IF NOT EXISTS pg_data (
	iddog numeric(28),
	data text
) ;
CREATE UNIQUE INDEX IF NOT EXISTS ku_pg_data ON pg_data (iddog);
ALTER TABLE pg_data alter column data type jsonb using data::JSON;
CREATE INDEX IF NOT EXISTS pg_data_id1 ON pg_data (iddog);
CREATE INDEX IF NOT EXISTS pg_data_id2 ON pg_data USING gin (data);
CREATE INDEX IF NOT EXISTS pg_data_id3 ON pg_data USING gin ((data->'dog'->'tokens'->'number') jsonb_path_ops);

CREATE TABLE IF NOT EXISTS pg_arbre_genealogie (
	id_chien numeric(28),
	id_pere numeric(28),
	id_mere numeric(28)
) ;
CREATE UNIQUE INDEX IF NOT EXISTS ku_pg_arbre_genealogie ON pg_arbre_genealogie (id_chien);
CREATE INDEX IF NOT EXISTS pg_arbre_genealogie_id1 ON pg_arbre_genealogie (id_chien);
CREATE INDEX IF NOT EXISTS pg_arbre_genealogie_id2 ON pg_arbre_genealogie (id_pere);
CREATE INDEX IF NOT EXISTS pg_arbre_genealogie_id3 ON pg_arbre_genealogie (id_mere);