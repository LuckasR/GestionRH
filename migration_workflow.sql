-- Script de migration pour ajouter les colonnes nécessaires au workflow de validation hiérarchique
-- À exécuter après avoir inséré les données de base (donne.sql)

-- 1. Ajouter la colonne superviseur_id dans la table employee
ALTER TABLE employee 
ADD COLUMN IF NOT EXISTS superviseur_id INT REFERENCES employee(id);

-- 2. Ajouter les colonnes pour le workflow de validation dans la table conge
ALTER TABLE conge 
ADD COLUMN IF NOT EXISTS superviseur_id INT REFERENCES employee(id),
ADD COLUMN IF NOT EXISTS statut_validation_superviseur_id INT REFERENCES status_general(id),
ADD COLUMN IF NOT EXISTS date_validation_superviseur TIMESTAMP;

-- 3. Mettre à jour quelques employés avec des superviseurs (exemple)
-- Remplacez les IDs par ceux de vos données réelles
-- UPDATE employee SET superviseur_id = 2 WHERE id = 1;
-- UPDATE employee SET superviseur_id = 2 WHERE id = 3;

-- 4. Vérification
SELECT 
    e1.id,
    e1.username as employe,
    e2.username as superviseur
FROM employee e1
LEFT JOIN employee e2 ON e1.superviseur_id = e2.id
LIMIT 10;

