CREATE TABLE reminder (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    message TEXT NOT NULL,
    title TEXT NOT NULL,
    sending_to TEXT NOT NULL,
    reminder_time TIMESTAMP WITH TIME ZONE NOT NULL,
    notification_type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

--Trigger para atualizar data de atualizacao dos registros
CREATE OR REPLACE FUNCTION set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_timestamp
BEFORE UPDATE ON reminder
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();