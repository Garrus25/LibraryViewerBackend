CREATE OR REPLACE FUNCTION update_average_rate() RETURNS TRIGGER AS $$
DECLARE
    avg_rate real;
BEGIN
    SELECT AVG(rate_value) INTO avg_rate FROM rate WHERE rated_object_id = NEW.rated_object_id AND rate_type = NEW.rate_type;

    IF NEW.rate_type = 'BOOK' THEN
        UPDATE books SET average_rate = avg_rate WHERE isbn = NEW.rated_object_id;
    ELSIF NEW.rate_type = 'AUTHOR' THEN
        UPDATE authors SET average_rate = avg_rate WHERE author_id = CAST(NEW.rated_object_id AS INTEGER);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;