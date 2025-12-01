CREATE TABLE departement (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE genre (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE methode (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE niveau_etude (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE filiere (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE siege_entreprise (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE type_contrat (
    id serial PRIMARY KEY, 
    name VARCHAR(100), 
    recurrence_renouvelement INT 
);

CREATE TABLE status_general (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE status_traitement (
    id serial PRIMARY KEY, 
    name VARCHAR(100) 
);

CREATE TABLE role (
    id serial PRIMARY KEY, 
    name VARCHAR(100), 
    niveau INT 
);

CREATE TABLE status_contrat (
    id serial PRIMARY KEY, 
    name VARCHAR(100)  
);

CREATE TABLE type_changement (
    id serial PRIMARY KEY, 
    name VARCHAR(100)  
);

CREATE TABLE type_compensation (
    id serial PRIMARY KEY, 
    name VARCHAR(100)  
);

CREATE TABLE poste (
    id serial PRIMARY KEY, 
    departement_id INT REFERENCES departement(id),
    name VARCHAR(100),
    salaire_base DECIMAL(10,2)
);

CREATE TABLE organisme_social (
    id serial PRIMARY KEY, 
    name VARCHAR(100), 
    pourcentage DECIMAL(5,2), 
    date_debut TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    date_fin TIMESTAMP DEFAULT NULL
);

CREATE TABLE societe (
    id serial PRIMARY KEY, 
    name VARCHAR(100),
    nombre_qcm_test INT DEFAULT 1,
    durre_entretient DECIMAL(5,2) DEFAULT 30.0, -- en minute
    pourcentage_passed INT, 
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE type_operation_conge (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE, --Demande approuvee, Demande refusee, Ajustement manuel
    description TEXT
);

CREATE TABLE type_evenement (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE, -- Conge approuve , Jour ferie  , reunion d'equipe
    description TEXT
);

CREATE TABLE admin (
    id serial PRIMARY KEY, 
    username VARCHAR(100), 
    password VARCHAR(100)
);

CREATE TABLE employee (
    id serial PRIMARY KEY, 
    role_id INT REFERENCES role(id),
    departement_id INT REFERENCES departement(id), 
    username VARCHAR(100), 
    password VARCHAR(100), 
    code_qr VARCHAR(250)
);

ALTER TABLE employee 
ADD COLUMN photo VARCHAR(255);


CREATE TABLE utilisateur (
    id serial PRIMARY KEY, 
    username VARCHAR(100), 
    password VARCHAR(100), 
    email VARCHAR(100)
);

CREATE TABLE contrat_employee (
    id serial PRIMARY KEY,     
    employee_id INT REFERENCES employee(id),
    date_debut DATE NOT NULL DEFAULT CURRENT_DATE, 
    date_fin DATE DEFAULT NULL,
    renouvellement_auto BOOLEAN DEFAULT FALSE, 
    has_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE detail_contrat_employee (
    id serial PRIMARY KEY, 
    admin_id INT REFERENCES admin(id),
    contrat_employee_id INT REFERENCES contrat_employee(id), -- lien avec le contrat de base
    type_contrat_id INT REFERENCES type_contrat(id),
    duree_contrat INT CHECK (duree_contrat > 0),  -- en mois
    date_debut_contrat DATE NOT NULL,
    date_fin_contrat DATE NOT NULL,
    periode_essai INT,  -- jours
    duree_preavis INT,           -- duree du preavis en jours
    statut_id INT REFERENCES status_contrat(id),
    renouvellement_auto BOOLEAN DEFAULT FALSE,
    commentaire TEXT
);

CREATE TABLE parcours_academique (
    id serial PRIMARY KEY, 
    employee_id INT REFERENCES employee(id),
    titre_parcours VARCHAR(150),  -- Ex: Licence en Informatique
    description TEXT
);

CREATE TABLE detail_parcours_academique (
    id serial PRIMARY KEY, 
    parcours_id INT REFERENCES parcours_academique(id),
    filiere_id INT REFERENCES filiere(id),
    niveau_etude_id INT REFERENCES niveau_etude(id),
    etablissement VARCHAR(150),   -- universite, ecole, etc.
    mention VARCHAR(50),          -- mention obtenue
    document_diplome VARCHAR(255), -- lien vers le fichier scanne
    date_obtention DATE
);

CREATE TABLE information_employee (
    id serial PRIMARY KEY, 
    employee_id INT REFERENCES employee(id),
    contrat_id INT REFERENCES contrat_employee(id), 
    salaire DECIMAL(10,2),
    first_name VARCHAR(100), 
    last_name VARCHAR(100), 
    email VARCHAR(100), 
    phone VARCHAR(20), 
    address VARCHAR(200), 
    cv VARCHAR(255), 
    lm VARCHAR(255), 
    cin VARCHAR(255), 
    residence VARCHAR(255), 
    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_naissance DATE 
);

CREATE TABLE historique_poste_employee (
    id serial PRIMARY KEY,
    admin_id INT REFERENCES admin(id),
    employee_id INT REFERENCES employee(id),
    poste_id INT REFERENCES poste(id),        -- poste actuel ou concerne
    type_changement_id INT REFERENCES type_changement(id),  -- Promotion, Mutation, etc.
    date_debut DATE NOT NULL,                 -- date de debut du changement (ou prise de poste)
    date_fin DATE,                            -- date de fin, si remplace par un autre poste
    commentaire TEXT,                         -- facultatif : raison ou detail du mouvement
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE assurance_social_employee (
    id serial PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    organisme_id INT REFERENCES organisme_social(id),
    date_attribution TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE historique_role (
    id serial PRIMARY KEY, 
    employee_id INT REFERENCES employee(id),
    role_id INT REFERENCES role(id),
    date_modification TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE type_conge (
    id serial PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,     -- Ex: "Conge paye", "Conge maladie", "Maternite"
    description TEXT,
    jours_attribues_annuels INT NOT NULL, -- nombre de jours par an
    is_payable BOOLEAN DEFAULT TRUE,      -- conge remunere ou non
    is_cumulable BOOLEAN DEFAULT TRUE,    -- peut s'accumuler d'une annee a l'autre
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE conge (
    id serial PRIMARY KEY,
    admin_id INT REFERENCES admin(id),
    employee_id INT REFERENCES employee(id),
    type_conge_id INT REFERENCES type_conge(id),
    nb_jours INT NOT NULL,
    commentaire TEXT,
    date_demande TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    statut_id INT REFERENCES status_general(id),
    date_validation TIMESTAMP,
    reference_demande VARCHAR(100)
);

CREATE TABLE solde_conge (
    id serial PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    type_conge_id INT REFERENCES type_conge(id),
    annee INT NOT NULL,
    jours_acquis INT DEFAULT 0,
    jours_pris INT DEFAULT 0,
    jours_restants INT DEFAULT 0,
    date_maj TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE historique_solde_conge (
    id serial PRIMARY KEY,
    solde_id INT REFERENCES solde_conge(id),
    type_operation_id INT REFERENCES type_operation_conge(id),
    jours_pris INT DEFAULT 0,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    commentaire TEXT,
    admin_id INT REFERENCES admin(id),
    date_action TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE calendrier_entreprise (
    id serial PRIMARY KEY,
    titre VARCHAR(150) NOT NULL,
    description TEXT,
    annee INT NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE evenement_calendrier (
    id serial PRIMARY KEY,
    calendrier_id INT REFERENCES calendrier_entreprise(id) ON DELETE CASCADE,
    employee_id INT REFERENCES employee(id),
    type_evenement_id INT REFERENCES type_evenement(id),
    titre VARCHAR(150) NOT NULL,
    description TEXT,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    statut INT REFERENCES status_general(id),
    couleur VARCHAR(20) DEFAULT '#0088ff',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE type_notification (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE notification (
    id serial PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    type_id INT REFERENCES type_notification(id),
    message TEXT NOT NULL,
    est_lu BOOLEAN DEFAULT FALSE,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pointage (
    id serial PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    date_pointage DATE NOT NULL,
    heure_arrivee TIME,
    heure_depart TIME DEFAULT NULL,
    methode_id INT REFERENCES methode(id),  -- manuel, badge, qr, mobile
    commentaire TEXT,
    date_enregistrement TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE regle_travail (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,        -- Ex: "Jour ouvre", "Samedi", "Dimanche"
    duree_normale INTERVAL NOT NULL DEFAULT INTERVAL '08:00',
    est_weekend BOOLEAN DEFAULT FALSE,
    est_ferie BOOLEAN DEFAULT FALSE,
    description TEXT
);

CREATE TABLE config_heure_supplementaire (
    id SERIAL PRIMARY KEY,
    regle_id INT REFERENCES regle_travail(id) ON DELETE CASCADE,
    heure_max_jour DECIMAL(5,2) DEFAULT 2.00,         -- max 2h par jour
    heure_max_semaine DECIMAL(5,2) DEFAULT 10.00,     -- max 10h par semaine
    taux_multiplicateur_jour DECIMAL(4,2) DEFAULT 1.25,  -- +25% pour les heures normales
    taux_multiplicateur_nuit DECIMAL(4,2) DEFAULT 1.50,  -- +50% pour les heures de nuit
    taux_multiplicateur_weekend DECIMAL(4,2) DEFAULT 2.00, -- +100% le weekend
    taux_multiplicateur_ferie DECIMAL(4,2) DEFAULT 3.00, -- +200% le ferie
    type_compensation INT REFERENCES type_compensation(id) DEFAULT 1, -- payee  // recuperation 
    besoin_validation_admin BOOLEAN DEFAULT TRUE,
    status_id INT REFERENCES status_general(id),
    admin_id INT REFERENCES admin(id) DEFAULT NULL,
    commentaire TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    date_fin TIMESTAMP DEFAULT NULL,
    est_actif BOOLEAN DEFAULT TRUE
);

CREATE TABLE heure_supplementaire (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    pointage_id INT REFERENCES pointage(id) ON DELETE CASCADE,
    config_id INT REFERENCES config_heure_supplementaire(id), -- 🔗 lie a la config
    nb_heures DECIMAL(5,2) NOT NULL, 
    type_compensation INT REFERENCES type_compensation(id) DEFAULT 1, -- payee  // recuperation 
    statut_id INT REFERENCES status_general(id), 
    admin_id INT REFERENCES admin(id),
    commentaire TEXT,
    date_validation TIMESTAMP DEFAULT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE retard (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    pointage_id INT REFERENCES pointage(id) ON DELETE CASCADE,
    minutes_retard INT NOT NULL,
    justification TEXT,
    statut_id INT REFERENCES status_general(id),  -- En attente, Justifie, Non justifie
    admin_id INT REFERENCES admin(id),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE absence (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    date_absence DATE NOT NULL,
    est_justifie BOOLEAN DEFAULT FALSE,
    commentaire TEXT,
    statut_id INT REFERENCES status_general(id),
    admin_id INT REFERENCES admin(id),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE parametre_taux (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,         -- Ex: 'CNAPS', 'OSTIE', 'IRSA'
    description TEXT,
    taux_employe DECIMAL(5,2) NOT NULL,       -- Pourcentage preleve sur le salaire employe
    taux_employeur DECIMAL(5,2) NOT NULL,     -- Pourcentage paye par l'entreprise
    date_application DATE DEFAULT CURRENT_DATE,
    date_fin DATE DEFAULT NULL, 
    actif BOOLEAN DEFAULT TRUE,
    UNIQUE (code, actif)
);

CREATE TABLE salaire_employee (
    id serial PRIMARY KEY, 
    admin_id INT REFERENCES admin(id), 
    employee_id INT REFERENCES employee(id),
    salaire DECIMAL(10,2), 
    motif_modification VARCHAR(100), 
    date_attribution DATE, 
    date_fin DATE DEFAULT NULL,
    est_actif BOOLEAN DEFAULT TRUE 
);

CREATE TABLE periode_paie (
    id SERIAL PRIMARY KEY,
    mois INT NOT NULL,
    annee INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    statut INT REFERENCES status_general(id),  -- ouverte, fermee
    admin_id INT REFERENCES admin(id),
    date_generation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (mois, annee)
);

CREATE TABLE paie_employee (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    periode_id INT REFERENCES periode_paie(id),
    salaire_base DECIMAL(10,2) NOT NULL,
    total_primes DECIMAL(10,2) DEFAULT 0,
    total_heures_sup DECIMAL(10,2) DEFAULT 0,
    total_absences DECIMAL(10,2) DEFAULT 0,
    total_deductions DECIMAL(10,2) DEFAULT 0,
    total_contributions DECIMAL(10,2) DEFAULT 0, -- irsa, cnaps, ostie
    net_a_payer DECIMAL(10,2) DEFAULT 0,
    date_calcul TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut INT REFERENCES status_general(id),  -- en_cours, paye
    admin_id INT REFERENCES admin(id), 
    UNIQUE (employee_id, periode_id)
);

CREATE TABLE paie_detail (
    id SERIAL PRIMARY KEY,
    paie_id INT REFERENCES paie_employee(id) ON DELETE CASCADE,
    type VARCHAR(50), -- 'prime', 'heure_sup', 'deduction', 'impot', etc.
    description TEXT,
    montant DECIMAL(10,2),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE log_action (
    id SERIAL PRIMARY KEY,
    admin_id INT REFERENCES admin(id),
    action_type VARCHAR(100),
    description TEXT,
    date_action TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX unique_salaire_actif
ON salaire_employee(employee_id)
WHERE (est_actif IS TRUE);

-- =====================================
-- INSERT DATA SECTION
-- Professional and extensive data insertion (multiple rows per table, respecting dependencies)
-- =====================================

-- Insert into independent tables first

-- departement (10 rows)
INSERT INTO departement (name) VALUES 
('Informatique et Technologie'), 
('Ressources Humaines'), 
('Finance et Comptabilite'), 
('Marketing et Ventes'), 
('Operations et Logistique'), 
('Recherche et Developpement'), 
('Service Client'), 
('Juridique'), 
('Administration Generale'), 
('Production');

-- genre (3 rows)
INSERT INTO genre (name) VALUES 
('Homme'), 
('Femme'), 
('Non-binaire');

-- methode (5 rows) - for pointage methods
INSERT INTO methode (name) VALUES 
('Manuel'), 
('Badge'), 
('QR Code'), 
('Mobile App'), 
('Biometrique');

-- niveau_etude (8 rows)
INSERT INTO niveau_etude (name) VALUES 
('Baccalaureat'), 
('Licence'), 
('Master'), 
('Doctorat'), 
('BTS'), 
('Ingenieur'), 
('MBA'), 
('Certificat Professionnel');

-- filiere (10 rows)
INSERT INTO filiere (name) VALUES 
('Informatique'), 
('Gestion d''Entreprise'), 
('Comptabilite'), 
('Marketing'), 
('Droit'), 
('Ingenierie Mecanique'), 
('Biologie'), 
('economie'), 
('Psychologie'), 
('Design Graphique');

-- siege_entreprise (7 rows)
INSERT INTO siege_entreprise (name) VALUES 
('Paris, France'), 
('New York, USA'), 
('Londres, UK'), 
('Berlin, Allemagne'), 
('Tokyo, Japon'), 
('Sydney, Australie'), 
('Madagascar, Antananarivo');

-- type_contrat (6 rows)
INSERT INTO type_contrat (name, recurrence_renouvelement) VALUES 
('CDI', 0), 
('CDD', 12), 
('Stage', 6), 
('Freelance', 3), 
('Interim', 1), 
('Apprentissage', 24);

-- status_general (5 rows)
INSERT INTO status_general (name) VALUES 
('En attente'), 
('Approuve'), 
('Rejete'), 
('En cours'), 
('Termine');

-- status_traitement (4 rows)
INSERT INTO status_traitement (name) VALUES 
('Non traite'), 
('En traitement'), 
('Traite'), 
('Archive');

-- role (8 rows)
INSERT INTO role (name, niveau) VALUES 
('Developpeur Junior', 1), 
('Developpeur Senior', 3), 
('Manager RH', 4), 
('Comptable', 2), 
('Directeur Financier', 5), 
('Ingenieur R&D', 3), 
('Assistant Marketing', 1), 
('Chef de Projet', 4);

-- status_contrat (5 rows)
INSERT INTO status_contrat (name) VALUES 
('Actif'), 
('Expire'), 
('Resilie'), 
('En probation'), 
('Renouvele');

-- type_changement (6 rows)
INSERT INTO type_changement (name) VALUES 
('Promotion'), 
('Mutation'), 
('Demotion'), 
('Transfert Departement'), 
('Augmentation Salaire'), 
('Changement de Rôle');

-- type_compensation (4 rows)
INSERT INTO type_compensation (name) VALUES 
('Paiement Monetaire'), 
('Recuperation Heures'), 
('Bonus Annuel'), 
('Avantages en Nature');

-- organisme_social (5 rows)
INSERT INTO organisme_social (name, pourcentage) VALUES 
('CNAPS', 13.00), 
('OSTIE', 8.00), 
('Assurance Maladie', 5.00), 
('Retraite Complementaire', 10.00), 
('Mutuelle Entreprise', 7.50);

-- societe (3 rows)
INSERT INTO societe (name, nombre_qcm_test, durre_entretient, pourcentage_passed) VALUES 
('TechCorp SA', 20, 45.0, 70), 
('FinanceGlobal Ltd', 15, 60.0, 80), 
('MarketInnov Inc', 10, 30.0, 65);

-- type_operation_conge (5 rows)
INSERT INTO type_operation_conge (nom, description) VALUES 
('Demande Approuvee', 'Conge approuve par l''administration'), 
('Demande Refusee', 'Conge refuse pour raisons operationnelles'), 
('Ajustement Manuel', 'Modification manuelle du solde par admin'), 
('Annulation', 'Annulation d''une demande precedente'), 
('Report', 'Report de jours de conge a l''annee suivante');

-- type_evenement (7 rows)
INSERT INTO type_evenement (nom, description) VALUES 
('Jour Ferie', 'Jour non travaille legal'), 
('Reunion d''equipe', 'Reunion interne avec l''equipe'), 
('Formation', 'Session de formation professionnelle'), 
('evenement Corporate', 'evenement d''entreprise'), 
('Audit', 'Audit interne ou externe'), 
('Team Building', 'Activite de renforcement d''equipe'), 
('Conference', 'Participation a une conference');

-- admin (5 rows) - hashed passwords for professionalism
INSERT INTO admin (username, password) VALUES 
('admin1', 'hashedpass123'), 
('admin2', 'hashedpass456'), 
('admin3', 'hashedpass789'), 
('admin4', 'hashedpass101'), 
('admin5', 'hashedpass202');

-- type_conge (8 rows)
INSERT INTO type_conge (libelle, description, jours_attribues_annuels) VALUES 
('Conge Paye', 'Conges annuels remuneres', 30), 
('Conge Maladie', 'Absence pour raisons medicales', 15), 
('Conge Maternite', 'Conge pour naissance', 90), 
('Conge Paternite', 'Conge pour les peres', 14), 
('Conge Sans Solde', 'Absence non remuneree', 0), 
('Conge Formation', 'Pour formation professionnelle', 10), 
('Conge Sabbatique', 'Pause carriere', 365), 
('Conge Exceptionnel', 'Pour evenements familiaux', 5);

-- type_notification (6 rows)
INSERT INTO type_notification (nom, description) VALUES 
('Alerte Conge', 'Notification liee aux conges'), 
('Mise a Jour Paie', 'Changements dans la paie'), 
('evenement Calendrier', 'Rappels d''evenements'), 
('Pointage Irregulier', 'Problemes de pointage'), 
('Promotion', 'Notifications de changements de poste'), 
('Urgence Administrative', 'Messages urgents admin');

-- regle_travail (7 rows)
INSERT INTO regle_travail (nom, duree_normale, est_weekend, est_ferie, description) VALUES 
('Jour Ouvre', INTERVAL '08:00', FALSE, FALSE, 'Jours de semaine normaux'), 
('Samedi', INTERVAL '04:00', TRUE, FALSE, 'Demi-journee weekend'), 
('Dimanche', INTERVAL '00:00', TRUE, TRUE, 'Jour de repos complet'), 
('Ferie National', INTERVAL '00:00', FALSE, TRUE, 'Jour ferie legal'), 
('Veille de Fete', INTERVAL '06:00', FALSE, FALSE, 'Jour avant ferie'), 
('Horaire Flexible', INTERVAL '07:30', FALSE, FALSE, 'Horaire adaptable'), 
('Teletravail', INTERVAL '08:00', FALSE, FALSE, 'Travail a distance');

-- parametre_taux (5 rows)
INSERT INTO parametre_taux (code, description, taux_employe, taux_employeur) VALUES 
('CNAPS', 'Caisse Nationale de Prevoyance Sociale', 1.00, 13.00), 
('OSTIE', 'Organisation Sanitaire Tananarivienne Inter-Entreprises', 1.00, 8.00), 
('IRSA', 'Impôt sur les Revenus Salariaux et Assimiles', 20.00, 0.00), 
('TVA', 'Taxe sur la Valeur Ajoutee', 0.00, 20.00), 
('CSG', 'Contribution Sociale Generalisee', 7.50, 9.20);

-- Now insert into dependent tables

-- poste (10 rows, referencing departement)
INSERT INTO poste (departement_id, name, salaire_base) VALUES 
(1, 'Developpeur Full Stack', 4500.00), 
(2, 'Specialiste RH', 3500.00), 
(3, 'Comptable Senior', 4000.00), 
(4, 'Manager Marketing', 5000.00), 
(5, 'Logisticien', 3200.00), 
(6, 'Chercheur R&D', 4800.00), 
(7, 'Support Client', 2800.00), 
(8, 'Avocat Interne', 5500.00), 
(9, 'Assistant Admin', 2500.00), 
(10, 'Operateur Production', 3000.00);

-- employee (10 rows, referencing role and departement)
INSERT INTO employee (role_id, departement_id, username, password, code_qr, photo) VALUES 
(1, 1, 'emp1', 'pass123', 'qr_emp1.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(2, 1, 'emp2', 'pass456', 'qr_emp2.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(3, 2, 'emp3', 'pass789', 'qr_emp3.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(4, 3, 'emp4', 'pass101', 'qr_emp4.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(5, 3, 'emp5', 'pass202', 'qr_emp5.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(6, 6, 'emp6', 'pass303', 'qr_emp6.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(7, 4, 'emp7', 'pass404', 'qr_emp7.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(8, 8, 'emp8', 'pass505', 'qr_emp8.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(1, 1, 'emp9', 'pass606', 'qr_emp9.png',  '1758184676288Yamaha_XMAX,_2020.jpg'), 
(2, 1, 'emp10', 'pass707', 'qr_emp10.png', '1758184676288Yamaha_XMAX,_2020.jpg');


-- utilisateur (10 rows)
INSERT INTO utilisateur (username, password, email) VALUES 
('user1', 'upass1', 'user1@email.com'), 
('user2', 'upass2', 'user2@email.com'), 
('user3', 'upass3', 'user3@email.com'), 
('user4', 'upass4', 'user4@email.com'), 
('user5', 'upass5', 'user5@email.com'), 
('user6', 'upass6', 'user6@email.com'), 
('user7', 'upass7', 'user7@email.com'), 
('user8', 'upass8', 'user8@email.com'), 
('user9', 'upass9', 'user9@email.com'), 
('user10', 'upass10', 'user10@email.com');

-- contrat_employee (10 rows, referencing employee)
INSERT INTO contrat_employee (employee_id, date_debut, date_fin, renouvellement_auto) VALUES 
(1, '2023-01-01', NULL, TRUE), 
(2, '2023-02-15', '2024-02-15', FALSE), 
(3, '2023-03-10', NULL, TRUE), 
(4, '2023-04-20', '2025-04-20', FALSE), 
(5, '2023-05-05', NULL, TRUE), 
(6, '2023-06-01', '2024-06-01', FALSE), 
(7, '2023-07-15', NULL, TRUE), 
(8, '2023-08-10', '2024-08-10', FALSE), 
(9, '2023-09-20', NULL, TRUE), 
(10, '2023-10-05', '2024-10-05', FALSE);

-- detail_contrat_employee (10 rows, referencing admin, contrat_employee, type_contrat, status_contrat)
INSERT INTO detail_contrat_employee (admin_id, contrat_employee_id, type_contrat_id, duree_contrat, date_debut_contrat, date_fin_contrat, periode_essai, duree_preavis, statut_id, commentaire) VALUES 
(1, 1, 1, 0, '2023-01-01', '9999-12-31', 90, 30, 1, 'Contrat permanent'), 
(2, 2, 2, 12, '2023-02-15', '2024-02-15', 30, 15, 1, 'Contrat temporaire'), 
(3, 3, 1, 0, '2023-03-10', '9999-12-31', 90, 30, 1, 'Contrat permanent'), 
(1, 4, 3, 6, '2023-04-20', '2023-10-20', 15, 7, 1, 'Stage'), 
(2, 5, 1, 0, '2023-05-05', '9999-12-31', 90, 30, 1, 'Contrat permanent'), 
(3, 6, 2, 12, '2023-06-01', '2024-06-01', 30, 15, 1, 'Contrat temporaire'), 
(1, 7, 1, 0, '2023-07-15', '9999-12-31', 90, 30, 1, 'Contrat permanent'), 
(2, 8, 4, 3, '2023-08-10', '2023-11-10', 0, 0, 1, 'Freelance'), 
(3, 9, 1, 0, '2023-09-20', '9999-12-31', 90, 30, 1, 'Contrat permanent'),
(1, 10, 2, 12, '2023-10-05', '2024-10-05', 30, 15, 1, 'Contrat temporaire');

-- parcours_academique (10 rows, referencing employee)
INSERT INTO parcours_academique (employee_id, titre_parcours, description) VALUES 
(1, 'Licence en Informatique', 'etudes en developpement logiciel'), 
(2, 'Master en Gestion', 'Specialisation en RH'), 
(3, 'Doctorat en Finance', 'Recherche en comptabilite avancee'), 
(4, 'BTS Marketing', 'Formation en ventes et promotion'), 
(5, 'Ingenieur Mecanique', 'etudes en ingenierie'), 
(6, 'MBA', 'Master en Administration des Affaires'), 
(7, 'Certificat Design', 'Formation graphique'), 
(8, 'Licence Droit', 'etudes juridiques'), 
(9, 'Master Informatique', 'Specialisation IA'), 
(10, 'Baccalaureat economie', 'Bases economiques');

-- detail_parcours_academique (10 rows, referencing parcours_academique, filiere, niveau_etude)
INSERT INTO detail_parcours_academique (parcours_id, filiere_id, niveau_etude_id, etablissement, mention, document_diplome, date_obtention) VALUES 
(1, 1, 2, 'Universite Paris-Saclay', 'Bien', 'diplome1.pdf', '2020-06-30'), 
(2, 2, 3, 'HEC Paris', 'Tres Bien', 'diplome2.pdf', '2019-07-15'), 
(3, 3, 4, 'Sorbonne Universite', 'Excellent', 'diplome3.pdf', '2018-05-20'), 
(4, 4, 5, 'IUT Marketing', 'Bien', 'diplome4.pdf', '2021-06-10'), 
(5, 6, 6, 'Polytechnique', 'Tres Bien', 'diplome5.pdf', '2017-09-01'), 
(6, 2, 7, 'ESSEC', 'Excellent', 'diplome6.pdf', '2022-01-15'), 
(7, 10, 8, 'ecole des Arts', 'Bien', 'diplome7.pdf', '2020-03-05'), 
(8, 5, 2, 'Universite Droit', 'Tres Bien', 'diplome8.pdf', '2016-12-20'), 
(9, 1, 3, 'MIT Equivalent', 'Excellent', 'diplome9.pdf', '2021-08-25'), 
(10, 8, 1, 'Lycee economique', 'Bien', 'diplome10.pdf', '2015-06-30');

-- information_employee (10 rows, referencing employee, contrat_employee)
INSERT INTO information_employee (employee_id, contrat_id, salaire, first_name, last_name, email, phone, address, cv, lm, cin, residence, date_naissance) VALUES 
(1, 1, 4500.00, 'Jean', 'Dupont', 'jean.dupont@email.com', '+33123456789', '123 Rue Paris', 'cv1.pdf', 'lm1.pdf', 'CIN123456', 'Paris', '1990-05-15'), 
(2, 2, 5000.00, 'Marie', 'Curie', 'marie.curie@email.com', '+33123456790', '456 Ave Lyon', 'cv2.pdf', 'lm2.pdf', 'CIN654321', 'Lyon', '1985-07-20'), 
(3, 3, 4000.00, 'Pierre', 'Martin', 'pierre.martin@email.com', '+33123456791', '789 Blvd Marseille', 'cv3.pdf', 'lm3.pdf', 'CIN112233', 'Marseille', '1992-03-10'), 
(4, 4, 3500.00, 'Sophie', 'Bernard', 'sophie.bernard@email.com', '+33123456792', '101 Rue Nice', 'cv4.pdf', 'lm4.pdf', 'CIN445566', 'Nice', '1988-11-05'), 
(5, 5, 4800.00, 'Luc', 'Petit', 'luc.petit@email.com', '+33123456793', '202 Ave Toulouse', 'cv5.pdf', 'lm5.pdf', 'CIN778899', 'Toulouse', '1995-01-25'), 
(6, 6, 5500.00, 'Anna', 'Leroy', 'anna.leroy@email.com', '+33123456794', '303 Blvd Bordeaux', 'cv6.pdf', 'lm6.pdf', 'CIN001122', 'Bordeaux', '1983-09-30'), 
(7, 7, 2800.00, 'Paul', 'Moreau', 'paul.moreau@email.com', '+33123456795', '404 Rue Lille', 'cv7.pdf', 'lm7.pdf', 'CIN334455', 'Lille', '1997-04-18'), 
(8, 8, 3200.00, 'Julie', 'Simon', 'julie.simon@email.com', '+33123456796', '505 Ave Strasbourg', 'cv8.pdf', 'lm8.pdf', 'CIN667788', 'Strasbourg', '1991-12-12'), 
(9, 9, 3000.00, 'Marc', 'Laurent', 'marc.laurent@email.com', '+33123456797', '606 Blvd Nantes', 'cv9.pdf', 'lm9.pdf', 'CIN990011', 'Nantes', '1986-06-08'), 
(10, 10, 5000.00, 'Emma', 'Michel', 'emma.michel@email.com', '+33123456798', '707 Rue Rennes', 'cv10.pdf', 'lm10.pdf', 'CIN223344', 'Rennes', '1994-02-22');

-- historique_poste_employee (10 rows, referencing admin, employee, poste, type_changement)
INSERT INTO historique_poste_employee (admin_id, employee_id, poste_id, type_changement_id, date_debut, date_fin, commentaire) VALUES 
(1, 1, 1, 1, '2023-01-01', NULL, 'Promotion initiale'), 
(2, 2, 2, 2, '2023-02-15', '2024-02-15', 'Mutation departement'), 
(3, 3, 3, 3, '2023-03-10', NULL, 'Demotion temporaire'), 
(1, 4, 4, 4, '2023-04-20', NULL, 'Transfert'), 
(2, 5, 5, 5, '2023-05-05', NULL, 'Augmentation'), 
(3, 6, 6, 6, '2023-06-01', '2024-06-01', 'Changement rôle'), 
(1, 7, 7, 1, '2023-07-15', NULL, 'Promotion'), 
(2, 8, 8, 2, '2023-08-10', NULL, 'Mutation'), 
(3, 9, 9, 3, '2023-09-20', NULL, 'Demotion'), 
(1, 10, 10, 4, '2023-10-05', NULL, 'Transfert');

-- assurance_social_employee (10 rows, referencing employee, organisme_social)
INSERT INTO assurance_social_employee (employee_id, organisme_id) VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 1), (7, 2), (8, 3), (9, 4), (10, 5);

-- historique_role (10 rows, referencing employee, role)
INSERT INTO historique_role (employee_id, role_id) VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 1), (10, 2);

-- conge (10 rows, referencing admin, employee, type_conge, status_general)
INSERT INTO conge (admin_id, employee_id, type_conge_id, nb_jours, commentaire, date_debut, date_fin, statut_id, date_validation, reference_demande) VALUES 
(1, 1, 1, 5, 'Vacances', '2024-01-01', '2024-01-05', 2, '2023-12-20', 'REF001'), 
(2, 2, 2, 3, 'Maladie', '2024-02-10', '2024-02-12', 2, '2024-02-05', 'REF002'), 
(3, 3, 3, 90, 'Maternite', '2024-03-15', '2024-06-15', 2, '2024-03-01', 'REF003'), 
(1, 4, 4, 14, 'Paternite', '2024-04-20', '2024-05-03', 2, '2024-04-10', 'REF004'), 
(2, 5, 5, 10, 'Sans solde', '2024-05-05', '2024-05-14', 3, NULL, 'REF005'), 
(3, 6, 6, 7, 'Formation', '2024-06-01', '2024-06-07', 2, '2024-05-25', 'REF006'), 
(1, 7, 7, 30, 'Sabbatique', '2024-07-15', '2024-08-14', 2, '2024-07-01', 'REF007'), 
(2, 8, 8, 2, 'Exceptionnel', '2024-08-10', '2024-08-11', 2, '2024-08-05', 'REF008'), 
(3, 9, 1, 4, 'Vacances', '2024-09-20', '2024-09-23', 2, '2024-09-10', 'REF009'), 
(1, 10, 2, 1, 'Maladie', '2024-10-05', '2024-10-05', 3, NULL, 'REF010');

-- solde_conge (10 rows, referencing employee, type_conge)
INSERT INTO solde_conge (employee_id, type_conge_id, annee, jours_acquis, jours_pris, jours_restants) VALUES 
(1, 1, 2024, 30, 5, 25), 
(2, 2, 2024, 15, 3, 12), 
(3, 3, 2024, 90, 0, 90), 
(4, 4, 2024, 14, 14, 0), 
(5, 5, 2024, 0, 10, -10), 
(6, 6, 2024, 10, 7, 3), 
(7, 7, 2024, 365, 30, 335), 
(8, 8, 2024, 5, 2, 3), 
(9, 1, 2024, 30, 4, 26), 
(10, 2, 2024, 15, 1, 14);

-- historique_solde_conge (10 rows, referencing solde_conge, type_operation_conge)
INSERT INTO historique_solde_conge (solde_id, type_operation_id, jours_pris, date_debut, date_fin, commentaire, admin_id) VALUES 
(1, 1, 5, '2024-01-01', '2024-01-05', 'Approuve', 1), 
(2, 1, 3, '2024-02-10', '2024-02-12', 'Approuve', 2), 
(3, 3, 0, '2024-03-15', '2024-06-15', 'Ajustement', 3), 
(4, 1, 14, '2024-04-20', '2024-05-03', 'Approuve', 1), 
(5, 2, 10, '2024-05-05', '2024-05-14', 'Refuse', 2), 
(6, 1, 7, '2024-06-01', '2024-06-07', 'Approuve', 3), 
(7, 1, 30, '2024-07-15', '2024-08-14', 'Approuve', 1), 
(8, 1, 2, '2024-08-10', '2024-08-11', 'Approuve', 2), 
(9, 1, 4, '2024-09-20', '2024-09-23', 'Approuve', 3), 
(10, 2, 1, '2024-10-05', '2024-10-05', 'Refuse', 1);

-- calendrier_entreprise (5 rows)
INSERT INTO calendrier_entreprise (titre, description, annee) VALUES 
('Calendrier 2024', 'Calendrier annuel entreprise', 2024), 
('Calendrier 2025', 'Projections futures', 2025), 
('Calendrier Feries', 'Jours feries', 2024), 
('Calendrier evenements', 'evenements internes', 2024), 
('Calendrier Formations', 'Sessions de formation', 2024);

-- evenement_calendrier (10 rows, referencing calendrier_entreprise, employee, type_evenement, status_general)
INSERT INTO evenement_calendrier (calendrier_id, employee_id, type_evenement_id, titre, description, date_debut, date_fin, statut, couleur) VALUES 
(1, 1, 1, 'Jour Ferie Noel', 'Vacances Noel', '2024-12-25', '2024-12-25', 5, '#FF0000'), 
(1, 2, 2, 'Reunion Mensuelle', 'Reunion equipe', '2024-01-10', '2024-01-10', 4, '#00FF00'), 
(2, 3, 3, 'Formation SQL', 'Cours avance', '2025-02-15', '2025-02-17', 1, '#0000FF'), 
(3, 4, 4, 'evenement Annuel', 'Gala entreprise', '2024-06-30', '2024-06-30', 2, '#FFFF00'), 
(4, 5, 5, 'Audit Financier', 'Verification comptes', '2024-03-20', '2024-03-22', 3, '#FF00FF'), 
(1, 6, 6, 'Team Building', 'Activite exterieure', '2024-04-05', '2024-04-05', 2, '#00FFFF'), 
(2, 7, 7, 'Conference Tech', 'Participation externe', '2025-05-10', '2025-05-12', 1, '#FFA500'), 
(3, 8, 1, 'Jour Ferie Paques', 'Vacances Paques', '2024-04-01', '2024-04-01', 5, '#808080'), 
(4, 9, 2, 'Reunion Projet', 'Discussion projet', '2024-07-15', '2024-07-15', 4, '#A52A2A'), 
(5, 10, 3, 'Formation Leadership', 'Developpement managerial', '2024-08-20', '2024-08-22', 2, '#008000');

-- notification (10 rows, referencing employee, type_notification)
INSERT INTO notification (employee_id, type_id, message) VALUES 
(1, 1, 'Votre conge a ete approuve'), 
(2, 2, 'Mise a jour de votre paie'), 
(3, 3, 'Rappel evenement calendrier'), 
(4, 4, 'Pointage irregulier detecte'), 
(5, 5, 'Felicitations pour votre promotion'), 
(6, 6, 'Message urgent admin'), 
(7, 1, 'Conge refuse, veuillez verifier'), 
(8, 2, 'Paie calculee pour le mois'), 
(9, 3, 'Nouvel evenement ajoute'), 
(10, 4, 'Retard enregistre, justification requise');

-- pointage (10 rows, referencing employee, methode)
INSERT INTO pointage (employee_id, date_pointage, heure_arrivee, heure_depart, methode_id, commentaire) VALUES 
(1, '2024-01-02', '08:00:00', '17:00:00', 1, 'Journee normale'), 
(2, '2024-01-03', '08:15:00', '17:30:00', 2, 'Leger retard'), 
(3, '2024-01-04', '07:45:00', '16:45:00', 3, 'Depart tôt'), 
(4, '2024-01-05', '09:00:00', '18:00:00', 4, 'Heures sup'), 
(5, '2024-01-06', '08:30:00', '17:00:00', 5, 'Teletravail'), 
(6, '2024-01-07', '08:00:00', NULL, 1, 'En cours'), 
(7, '2024-01-08', '08:05:00', '17:10:00', 2, 'Normal'), 
(8, '2024-01-09', '07:55:00', '16:55:00', 3, 'Precoce'), 
(9, '2024-01-10', '08:20:00', '17:20:00', 4, 'Retard justifie'), 
(10, '2024-01-11', '08:00:00', '17:00:00', 5, 'Standard');

-- config_heure_supplementaire (7 rows, referencing regle_travail, type_compensation, status_general, admin)
INSERT INTO config_heure_supplementaire (regle_id, heure_max_jour, heure_max_semaine, taux_multiplicateur_jour, taux_multiplicateur_nuit, taux_multiplicateur_weekend, taux_multiplicateur_ferie, type_compensation, besoin_validation_admin, status_id, admin_id, commentaire) VALUES 
(1, 2.00, 10.00, 1.25, 1.50, 2.00, 3.00, 1, TRUE, 1, 1, 'Config standard'), 
(2, 1.50, 8.00, 1.30, 1.60, 2.50, 3.50, 2, TRUE, 2, 2, 'Config weekend'), 
(3, 0.00, 0.00, 1.00, 1.00, 1.00, 1.00, 3, FALSE, 3, 3, 'Pas de sup'), 
(4, 3.00, 15.00, 1.50, 2.00, 3.00, 4.00, 4, TRUE, 4, 1, 'Config ferie'), 
(5, 2.50, 12.00, 1.40, 1.70, 2.20, 3.20, 1, TRUE, 5, 2, 'Config veille'), 
(6, 2.00, 10.00, 1.25, 1.50, 2.00, 3.00, 2, TRUE, 1, 3, 'Flexible'), 
(7, 1.00, 5.00, 1.10, 1.20, 1.50, 2.00, 3, FALSE, 2, 1, 'Teletravail');

-- heure_supplementaire (10 rows, referencing employee, pointage, config_heure_supplementaire, type_compensation, status_general, admin)
INSERT INTO heure_supplementaire (employee_id, pointage_id, config_id, nb_heures, type_compensation, statut_id, admin_id, commentaire, date_validation) VALUES 
(1, 1, 1, 2.00, 1, 2, 1, 'Heures sup jour', '2024-01-03'), 
(2, 2, 2, 1.50, 2, 2, 2, 'Heures sup weekend', '2024-01-04'), 
(3, 3, 3, 0.00, 3, 3, 3, 'Aucune', NULL), 
(4, 4, 4, 3.00, 4, 2, 1, 'Heures ferie', '2024-01-06'), 
(5, 5, 5, 2.50, 1, 2, 2, 'Heures veille', '2024-01-07'), 
(6, 6, 6, 2.00, 2, 1, 3, 'Flexible', NULL), 
(7, 7, 7, 1.00, 3, 2, 1, 'Teletravail', '2024-01-09'), 
(8, 8, 1, 0.50, 1, 3, 2, 'Minimal', NULL), 
(9, 9, 2, 1.00, 2, 2, 3, 'Sup retard', '2024-01-11'), 
(10, 10, 3, 0.00, 3, 2, 1, 'Aucune', '2024-01-12');

-- retard (10 rows, referencing employee, pointage, status_general, admin)
INSERT INTO retard (employee_id, pointage_id, minutes_retard, justification, statut_id, admin_id) VALUES 
(1, 1, 0, 'Aucun', 2, 1), 
(2, 2, 15, 'Trafic', 2, 2), 
(3, 3, 0, 'Aucun', 3, 3), 
(4, 4, 60, 'Maladie', 2, 1), 
(5, 5, 30, 'Rendez-vous', 2, 2), 
(6, 6, 0, 'Aucun', 1, 3), 
(7, 7, 5, 'Petit retard', 2, 1), 
(8, 8, 0, 'Aucun', 3, 2), 
(9, 9, 20, 'Transport', 2, 3), 
(10, 10, 0, 'Aucun', 2, 1);

-- absence (10 rows, referencing employee, status_general, admin)
INSERT INTO absence (employee_id, date_absence, est_justifie, commentaire, statut_id, admin_id) VALUES 
(1, '2024-01-12', TRUE, 'Maladie', 2, 1), 
(2, '2024-01-13', FALSE, 'Non justifie', 3, 2), 
(3, '2024-01-14', TRUE, 'Conge', 2, 3), 
(4, '2024-01-15', TRUE, 'Formation', 2, 1), 
(5, '2024-01-16', FALSE, 'Absence imprevue', 1, 2), 
(6, '2024-01-17', TRUE, 'Personnel', 2, 3), 
(7, '2024-01-18', TRUE, 'Maladie', 2, 1), 
(8, '2024-01-19', FALSE, 'Non justifie', 3, 2), 
(9, '2024-01-20', TRUE, 'Vacances', 2, 3), 
(10, '2024-01-21', TRUE, 'evenement', 2, 1);

-- salaire_employee (10 rows, referencing admin, employee)
INSERT INTO salaire_employee (admin_id, employee_id, salaire, motif_modification, date_attribution, est_actif) VALUES 
(1, 1, 4500.00, 'Salaire initial', '2023-01-01', TRUE), 
(2, 2, 5000.00, 'Augmentation', '2023-02-15', TRUE), 
(3, 3, 4000.00, 'Salaire initial', '2023-03-10', TRUE), 
(1, 4, 3500.00, 'Salaire stage', '2023-04-20', TRUE), 
(2, 5, 4800.00, 'Salaire initial', '2023-05-05', TRUE), 
(3, 6, 5500.00, 'Augmentation', '2023-06-01', TRUE), 
(1, 7, 2800.00, 'Salaire initial', '2023-07-15', TRUE), 
(2, 8, 3200.00, 'Salaire freelance', '2023-08-10', TRUE), 
(3, 9, 3000.00, 'Salaire initial', '2023-09-20', TRUE), 
(1, 10, 5000.00, 'Augmentation', '2023-10-05', TRUE);

-- periode_paie (12 rows for a year)
INSERT INTO periode_paie (mois, annee, date_debut, date_fin, statut, admin_id) VALUES 
(1, 2024, '2024-01-01', '2024-01-31', 2, 1), 
(2, 2024, '2024-02-01', '2024-02-29', 2, 2), 
(3, 2024, '2024-03-01', '2024-03-31', 2, 3), 
(4, 2024, '2024-04-01', '2024-04-30', 1, 1), 
(5, 2024, '2024-05-01', '2024-05-31', 2, 2), 
(6, 2024, '2024-06-01', '2024-06-30', 2, 3), 
(7, 2024, '2024-07-01', '2024-07-31', 2, 1), 
(8, 2024, '2024-08-01', '2024-08-31', 1, 2), 
(9, 2024, '2024-09-01', '2024-09-30', 2, 3), 
(10, 2024, '2024-10-01', '2024-10-31', 2, 1), 
(11, 2024, '2024-11-01', '2024-11-30', 2, 2), 
(12, 2024, '2024-12-01', '2024-12-31', 1, 3);

-- paie_employee (10 rows, referencing employee, periode_paie, status_general, admin)
INSERT INTO paie_employee (employee_id, periode_id, salaire_base, total_primes, total_heures_sup, total_absences, total_deductions, total_contributions, net_a_payer, statut, admin_id) VALUES 
(1, 1, 4500.00, 500.00, 200.00, 100.00, 300.00, 400.00, 4400.00, 2, 1), 
(2, 2, 5000.00, 600.00, 250.00, 150.00, 350.00, 450.00, 4900.00, 2, 2), 
(3, 3, 4000.00, 400.00, 150.00, 50.00, 200.00, 300.00, 4000.00, 2, 3), 
(4, 4, 3500.00, 300.00, 100.00, 0.00, 150.00, 200.00, 3550.00, 1, 1), 
(5, 5, 4800.00, 700.00, 300.00, 200.00, 400.00, 500.00, 4700.00, 2, 2), 
(6, 6, 5500.00, 800.00, 350.00, 250.00, 450.00, 550.00, 5400.00, 2, 3), 
(7, 7, 2800.00, 200.00, 100.00, 50.00, 100.00, 150.00, 2800.00, 2, 1), 
(8, 8, 3200.00, 250.00, 150.00, 100.00, 200.00, 250.00, 3050.00, 1, 2), 
(9, 9, 3000.00, 300.00, 200.00, 150.00, 250.00, 300.00, 2800.00, 2, 3), 
(10, 10, 5000.00, 600.00, 250.00, 200.00, 350.00, 450.00, 4850.00, 2, 1);

-- paie_detail (20 rows, referencing paie_employee - 2 per paie for variety)
INSERT INTO paie_detail (paie_id, type, description, montant) VALUES 
(1, 'prime', 'Prime performance', 500.00), 
(1, 'heure_sup', 'Heures supplementaires', 200.00), 
(2, 'prime', 'Prime anciennete', 600.00), 
(2, 'heure_sup', 'Heures nuit', 250.00), 
(3, 'prime', 'Prime equipe', 400.00), 
(3, 'heure_sup', 'Heures weekend', 150.00), 
(4, 'prime', 'Prime projet', 300.00), 
(4, 'heure_sup', 'Heures ferie', 100.00), 
(5, 'prime', 'Prime innovation', 700.00), 
(5, 'heure_sup', 'Heures extra', 300.00), 
(6, 'prime', 'Prime leadership', 800.00), 
(6, 'heure_sup', 'Heures sup', 350.00), 
(7, 'prime', 'Prime base', 200.00), 
(7, 'heure_sup', 'Heures minimales', 100.00), 
(8, 'prime', 'Prime freelance', 250.00), 
(8, 'heure_sup', 'Heures variables', 150.00), 
(9, 'prime', 'Prime standard', 300.00), 
(9, 'heure_sup', 'Heures jour', 200.00), 
(10, 'prime', 'Prime avancee', 600.00), 
(10, 'heure_sup', 'Heures nuit', 250.00);

-- log_action (10 rows, referencing admin)
INSERT INTO log_action (admin_id, action_type, description) VALUES 
(1, 'Creation Employe', 'Ajout de nouveau employe'), 
(2, 'Mise a Jour Contrat', 'Renouvellement contrat'), 
(3, 'Validation Conge', 'Approbation demande conge'), 
(1, 'Calcul Paie', 'Generation bulletin paie'), 
(2, 'Ajout evenement', 'Creation evenement calendrier'), 
(3, 'Notification Envoyee', 'Envoi alerte a employe'), 
(1, 'Pointage Manuel', 'Correction pointage'), 
(2, 'Config Heures Sup', 'Mise a jour config'), 
(3, 'Absence Justifiee', 'Validation absence'), 
(1, 'Log Admin', 'Action generale admin');