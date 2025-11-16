-- Script SQL pour corriger le type de la colonne duree_normale
-- Option 1 : Si vous voulez garder INTERVAL (recommandé pour les durées)
-- Le modèle Java utilise maintenant String pour mapper INTERVAL

-- Option 2 : Si vous préférez utiliser INTEGER (modifier la base de données)
-- Décommentez les lignes suivantes si vous voulez changer le type en INTEGER :

-- ALTER TABLE regle_travail 
-- ALTER COLUMN duree_normale TYPE INTEGER 
-- USING EXTRACT(EPOCH FROM duree_normale)::INTEGER / 3600; -- Convertit en heures

-- Note : La solution actuelle utilise String pour mapper INTERVAL PostgreSQL
-- Ceci est la meilleure approche car elle préserve la sémantique des durées

