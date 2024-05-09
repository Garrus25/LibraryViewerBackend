CREATE TRIGGER update_average_rate_after_insert
    AFTER INSERT ON rate
    FOR EACH ROW
EXECUTE FUNCTION update_average_rate();

CREATE TRIGGER update_average_rate_after_update
    AFTER UPDATE ON rate
    FOR EACH ROW
EXECUTE FUNCTION update_average_rate();