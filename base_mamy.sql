-- =============================================
-- 1. CLEANUP (Optional: Reset tables if they exist)
-- =============================================
DROP TABLE IF EXISTS parametre_detail;
DROP TABLE IF EXISTS parametre_taux;
DROP TABLE IF EXISTS taux;

-- =============================================
-- 2. TABLE CREATION
-- =============================================

-- Table 1: TAUX (The reusable rates)
CREATE TABLE taux(
   id SERIAL PRIMARY KEY,
   code_taux VARCHAR(20), 
   taux_employe DECIMAL(15,2) NOT NULL,
   taux_employeur DECIMAL(15,2)
);

-- Table 2: PARAMETRE (The Rule Header)
CREATE TABLE parametre_taux(
   id SERIAL PRIMARY KEY,
   code VARCHAR(20) NOT NULL, 
   description VARCHAR(100) NOT NULL,
   date_debut_application DATE DEFAULT CURRENT_DATE,
   date_fin DATE DEFAULT NULL,
   actif BOOLEAN DEFAULT TRUE
);

-- Table 3: PARAMETRE_DETAIL (The Configuration / Logic)
CREATE TABLE parametre_detail(
   id SERIAL PRIMARY KEY,
   id_parametre INT NOT NULL,
   id_taux INT NOT NULL,
   montant_min DECIMAL(19,4) NULL, 
   montant_max DECIMAL(19,4) NULL,
   FOREIGN KEY(id_parametre) REFERENCES parametre_taux(id),
   FOREIGN KEY(id_taux) REFERENCES taux(id),
   CONSTRAINT chk_detail_valide CHECK (
       (montant_min IS NULL OR montant_max IS NULL OR montant_max > montant_min)
   )
);

-- =============================================
-- 3. DATA INSERTION
-- =============================================

-- A. INSERT RATES (TAUX)
-- We define all unique rates found in your data, plus the detailed IRSA rates.
INSERT INTO taux (code_taux, taux_employe, taux_employeur) VALUES 
('T_CNAPS',   1.00, 13.00),  -- From your input
('T_OSTIE',   1.00,  8.00),  -- From your input
('T_TVA',     0.00, 20.00),  -- From your input
('T_CSG',     7.50,  9.20),  -- From your input
('T_IRSA_00', 0.00,  0.00),  -- IRSA Bracket 1
('T_IRSA_05', 5.00,  0.00),  -- IRSA Bracket 2
('T_IRSA_10',10.00,  0.00),  -- IRSA Bracket 3
('T_IRSA_15',15.00,  0.00),  -- IRSA Bracket 4
('T_IRSA_20',20.00,  0.00),  -- IRSA Bracket 5 (Matches your input max)
('T_IRSA_25',25.00,  0.00);  -- IRSA Bracket 6 (Matches your input max)


-- B. INSERT PARAMETERS (HEADERS)
INSERT INTO parametre_taux (code, description) VALUES 
('CNAPS', 'Caisse Nationale de Prevoyance Sociale'),
('OSTIE', 'Organisation Sanitaire Tananarivienne Inter-Entreprises'),
('TVA',   'Taxe sur la Valeur Ajoutee'),
('CSG',   'Contribution Sociale Generalisee'),
('IRSA',  'Impôt sur les Revenus Salariaux et Assimiles');

-- C. INSERT DETAILS (LINKING RULES)

-- 1. CNAPS (Global Rule - No Limit)
INSERT INTO parametre_detail (id_parametre, id_taux, montant_min, montant_max)
VALUES (
    (SELECT id FROM parametre_taux WHERE code='CNAPS'),
    (SELECT id FROM taux WHERE code_taux='T_CNAPS'),
    NULL, NULL
);

-- 2. OSTIE (Global Rule - No Limit)
INSERT INTO parametre_detail (id_parametre, id_taux, montant_min, montant_max)
VALUES (
    (SELECT id FROM parametre_taux WHERE code='OSTIE'),
    (SELECT id FROM taux WHERE code_taux='T_OSTIE'),
    NULL, NULL
);

-- 3. TVA (Global Rule - No Limit)
INSERT INTO parametre_detail (id_parametre, id_taux, montant_min, montant_max)
VALUES (
    (SELECT id FROM parametre_taux WHERE code='TVA'),
    (SELECT id FROM taux WHERE code_taux='T_TVA'),
    NULL, NULL
);

-- 4. CSG (Global Rule - No Limit)
INSERT INTO parametre_detail (id_parametre, id_taux, montant_min, montant_max)
VALUES (
    (SELECT id FROM parametre_taux WHERE code='CSG'),
    (SELECT id FROM taux WHERE code_taux='T_CSG'),
    NULL, NULL
);

-- 5. IRSA (Progressive Brackets)
-- This demonstrates the power of the schema. One parameter (IRSA) has 4 lines of configuration.
INSERT INTO parametre_detail (id_parametre, id_taux, montant_min, montant_max)
VALUES 
-- Bracket 0% (0 to 350,000)
(
    (SELECT id FROM parametre_taux WHERE code='IRSA'),
    (SELECT id FROM taux WHERE code_taux='T_IRSA_00'),
    0, 350000
),
-- Bracket 5% (350,000 to 400,000)
(
    (SELECT id FROM parametre_taux WHERE code='IRSA'),
    (SELECT id FROM taux WHERE code_taux='T_IRSA_05'),
    350001, 400000
),
-- Bracket 10% (400,000 to 2,000,000)
(
    (SELECT id FROM parametre_taux WHERE code='IRSA'),
    (SELECT id FROM taux WHERE code_taux='T_IRSA_10'),
    400001, 500000
),
-- Bracket 20% (Above 2,000,000)
(
    (SELECT id FROM parametre_taux WHERE code='IRSA'),
    (SELECT id FROM taux WHERE code_taux='T_IRSA_15'),
    500001, 600000
),
(
    (SELECT id FROM parametre_taux WHERE code='IRSA'),
    (SELECT id FROM taux WHERE code_taux='T_IRSA_20'),
    600001, 4000000
),
(
    (SELECT id FROM parametre_taux WHERE code='IRSA'),
    (SELECT id FROM taux WHERE code_taux='T_IRSA_25'),
    4000000, NULL
);