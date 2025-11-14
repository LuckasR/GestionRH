create table departement (
    id serial primary key , 
    name varchar(100) 
) ;

create table genre (
    id serial primary key , 
    name varchar(100) 
) ;

create table methode(
    id serial primary key , 
    name varchar(100) 
) ;

create table niveau_etude (
    id serial primary key , 
    name varchar(100) 
) ;


create table filiere (
    id serial primary key , 
    name varchar(100) 
) ;

create table siege_entreprise (
    id serial primary key , 
    name varchar(100) 
) ;
 
create table type_contrat (
    id serial primary key , 
    name varchar(100) , 
    recurrence_renouvelement int 
) ;

create table status_general( 
    id serial primary key , 
    name varchar(100) 
) ; 

create table status_traitement( 
    id serial primary key , 
    name varchar(100) 
) ; 

create table role (
    id serial primary key , 
    name varchar(100)   , 
    niveau int 
) ; 

create table status_contrat (
    id serial primary key , 
    name varchar(100)  
) ; 

create table type_changement(
    id serial primary key , 
    name varchar(100)  
) ; 
 
create table type_compensation(
    id serial primary key , 
    name varchar(100)  
) ; 

create table poste (
    id serial primary key , 
    departement_id int REFERENCES departement(id) ,
    name varchar(100)  ,
    salaire_base decimal(10,2)
) ; 


--Cnaps, Ostie
create table organisme_social (
    id serial primary key , 
    name varchar(100)   , 
    pourcentage decimal(5,2) , 
    date_debut timestamp default current_timestamp , 
    date_fin timestamp default null
) ; 

create table societe (
  id serial primary key , 
  name varchar(100) ,
  nombre_qcm_test int default 1 ,
  durre_entretient decimal(5,2) default 30.0 , -- en minute
  pourcentage_passed int , 
  date_creation timestamp  default current_timestamp
) ; 
 
 -- Begin Gestion personnel
create table admin  (
    id serial primary key , 
    username varchar(100) , 
    password varchar(100)
) ;

create table employee  (
    id serial primary key , 
    role_id int REFERENCES role(id) ,
    departement_id int REFERENCES departement(id) , 
    username varchar(100) , 
    password varchar(100) 
) ; 
-- alter table employee add column departement_id int REFERENCES departement(id) ; 

create table utilisateur  (
    id serial primary key , 
    username varchar(100) , 
    password varchar(100) , 
    email varchar(100)
) ; 

-- alter table utilisateur add column email varchar(100) ;

create table contrat_employee  (
    id serial primary key ,     
    employee_id int REFERENCES employee(id) ,
    date_debut date not null default CURRENT_DATE  , 
    date_fin date default null ,
    renouvellement_auto boolean default false, 
    has_active boolean DEFAULT true
) ;


create table detail_contrat_employee (
    id serial primary key, 
    admin_id int references admin(id),
    contrat_employee_id int references contrat_employee(id), -- lien avec le contrat de base
    type_contrat_id int references type_contrat(id),
    duree_contrat int  CHECK (duree_contrat > 0) ,  -- en mois
    date_debut_contrat date not null,
    date_fin_contrat date not null,
    periode_essai int,  -- jours
    duree_preavis int,           -- durée du préavis en jours
    statut_id int references status_contrat(id),
    renouvellement_auto boolean default false,
    commentaire text
);


create table parcours_academique (
    id serial primary key , 
    employee_id int REFERENCES employee(id) ,
    titre_parcours varchar(150),  -- Ex: Licence en Informatique
    description text
) ; 


create table detail_parcours_academique (
    id serial primary key , 
    parcours_id int REFERENCES parcours_academique(id) ,
    filiere_id int REFERENCES filiere(id) ,
    niveau_etude_id int REFERENCES niveau_etude(id) ,
    etablissement varchar(150),   -- université, école, etc.
    mention varchar(50),          -- mention obtenue
    document_diplome varchar(255), -- lien vers le fichier scanné
    date_obtention date
) ; 


create table information_employee (
    id serial primary key , 
    employee_id int REFERENCES employee(id) ,
    contrat_id int REFERENCES contrat_employee(id) , 
    salaire decimal(10,2) ,
    first_name varchar(100) , 
    last_name varchar(100) , 
    email varchar(100) , 
    phone varchar(20) , 
    address VARCHAR(200) , 
    cv varchar(255) , 
    lm varchar(255) , 
    cin varchar(255) , 
    residence varchar(255) , 
    date_creation timestamp not null default current_timestamp ,
    date_naissance date 
) ;


create table historique_poste_employee (
    id serial primary key,
    admin_id int references admin(id),
    employee_id int references employee(id),
    poste_id int references poste(id),        -- poste actuel ou concerné
    type_changement_id int references type_changement(id),  -- Promotion, Mutation, etc.
    date_debut date not null,                 -- date de début du changement (ou prise de poste)
    date_fin date,                            -- date de fin, si remplacé par un autre poste
    commentaire text,                         -- facultatif : raison ou détail du mouvement
    date_creation timestamp default current_timestamp
);


create table assurance_social_employee(
    id serial primary key ,
    employee_id int REFERENCES employee(id) ,
    organisme_id int REFERENCES organisme_social(id) ,
    date_attribution timestamp not null default current_timestamp
) ;

create table historique_role (
    id serial primary key , 
    employee_id int REFERENCES employee(id) ,
    role_id int REFERENCES role(id) ,
    date_modification timestamp not null default current_timestamp
) ;


create table type_conge (
    id serial primary key,
    libelle varchar(100) not null,     -- Ex: "Congé payé", "Congé maladie", "Maternité"
    description text,
    jours_attribues_annuels int not null, -- nombre de jours par an
    is_payable boolean default true,      -- congé rémunéré ou non
    is_cumulable boolean default true,    -- peut s'accumuler d'une année à l'autre
    date_creation timestamp default current_timestamp
);

 
-- CONGÉ (demande + validation)
create table conge (
    id serial primary key,
    admin_id int references admin(id),
    employee_id int references employee(id),
    type_conge_id int references type_conge(id),
    nb_jours int not null,
    commentaire text,
    date_demande timestamp default current_timestamp,
    date_debut date not null,
    date_fin date not null,
    statut_id int references status_general(id),
    date_validation timestamp,
    reference_demande varchar(100)
);

-- SOLDE DE CONGÉ PAR EMPLOYÉ
create table solde_conge (
    id serial primary key,
    employee_id int references employee(id),
    type_conge_id int references type_conge(id),
    annee int not null,
    jours_acquis int default 0,
    jours_pris int default 0,
    jours_restants int default 0,
    date_maj timestamp default current_timestamp
);

-- TYPE D’OPÉRATION SUR LE SOLDE
create table type_operation_conge (
    id serial primary key,
    nom varchar(100) not null unique, --Demande approuvée, Demande refusée, Ajustement manuel
    description text
);

-- HISTORIQUE DES MODIFICATIONS DE SOLDE
create table historique_solde_conge (
    id serial primary key,
    solde_id int references solde_conge(id),
    type_operation_id int references type_operation_conge(id),
    jours_pris int default 0,
    date_debut date not null,
    date_fin date not null,
    commentaire text,
    admin_id int references admin(id),
    date_action timestamp default current_timestamp
);

-- CALENDRIER GLOBAL (optionnel, pour tous les événements)
create table calendrier_entreprise (
    id serial primary key,
    titre varchar(150) not null,
    description text,
    annee int not null,
    date_creation timestamp default current_timestamp
);

-- TYPES D'ÉVÉNEMENTS GÉNÉRAUX
create table type_evenement (
    id serial primary key,
    nom varchar(100) not null unique, -- Congé approuvé , Jour férié  , reunion d'équipe
    description text
);

-- ÉVÉNEMENTS (hors congé)
create table evenement_calendrier (
    id serial primary key,
    calendrier_id int references calendrier_entreprise(id) on delete cascade,
    employee_id int references employee(id),
    type_evenement_id int references type_evenement(id),
    titre varchar(150) not null,
    description text,
    date_debut date not null,
    date_fin date not null,
    statut int references status_general(id),
    couleur varchar(20) default '#0088ff',
    date_creation timestamp default current_timestamp
);


create table type_notification (
    id serial primary key,
    nom varchar(100) not null unique,
    description text
);


create table notification (
    id serial primary key,
    employee_id int references employee(id),
    type_id int references type_notification(id),
    message text not null,
    est_lu boolean default false,
    date_creation timestamp default current_timestamp
);


create table pointage (
    id serial primary key,
    employee_id int references employee(id),
    date_pointage date not null,
    heure_arrivee time,
    heure_depart time DEFAULT null ,
    methode_id int REFERENCES methode(id),  -- manuel, badge, qr, mobile
    commentaire text,
    date_enregistrement timestamp default current_timestamp
);

create table regle_travail (
    id serial primary key,
    nom varchar(100) not null,        -- Ex: "Jour ouvré", "Samedi", "Dimanche"
    duree_normale interval not null default interval '08:00',
    est_weekend boolean default false,
    est_ferie boolean default false,
    description text
);
CREATE TABLE config_heure_supplementaire (
    id SERIAL PRIMARY KEY,
    regle_id INT REFERENCES regle_travail(id) ON DELETE CASCADE,
    heure_max_jour DECIMAL(5,2) DEFAULT 2.00,         -- max 2h par jour
    heure_max_semaine DECIMAL(5,2) DEFAULT 10.00,     -- max 10h par semaine
    taux_multiplicateur_jour DECIMAL(4,2) DEFAULT 1.25,  -- +25% pour les heures normales
    taux_multiplicateur_nuit DECIMAL(4,2) DEFAULT 1.50,  -- +50% pour les heures de nuit
    taux_multiplicateur_weekend DECIMAL(4,2) DEFAULT 2.00, -- +100% le weekend
    taux_multiplicateur_ferie DECIMAL(4,2) DEFAULT 3.00, -- +200% le ferié
    type_compensation int REFERENCES type_compensation(id) DEFAULT 1 , -- payee  // recuperation 
    besoin_validation_admin BOOLEAN DEFAULT TRUE,
    status_id INT REFERENCES status_general(id),
    admin_id INT REFERENCES admin(id) default null,
    commentaire TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP , 
    date_fin TIMESTAMP DEFAULT NULL ,
    est_actif BOOLEAN DEFAULT TRUE
);
-- montant = nb_heures * (salaire_base / 173.33) * taux_multiplicateur

CREATE TABLE heure_supplementaire (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(id),
    pointage_id INT REFERENCES pointage(id) ON DELETE CASCADE,
    config_id INT REFERENCES config_heure_supplementaire(id), -- 🔗 lie à la config
    nb_heures DECIMAL(5,2) NOT NULL, 
    type_compensation int REFERENCES type_compensation(id) DEFAULT 1 , -- payee  // recuperation 
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
    statut_id INT REFERENCES status_general(id),  -- En attente, Justifié, Non justifié
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
    taux_employe DECIMAL(5,2) NOT NULL,       -- Pourcentage prélevé sur le salaire employé
    taux_employeur DECIMAL(5,2) NOT NULL,     -- Pourcentage payé par l'entreprise
    date_application DATE DEFAULT CURRENT_DATE,
    date_fin date DEFAULT null , 
    actif BOOLEAN DEFAULT TRUE ,
    UNIQUE (code, actif)
);


create table salaire_employee(
    id serial primary key , 
    admin_id int REFERENCES admin(id) , 
    employee_id int REFERENCES employee(id) ,
    salaire decimal(10,2) , 
    motif_modification VARCHAR(100) , 
    date_attribution date , 
    date_fin date default null ,
    est_actif boolean default true 
);


-- Table des périodes de paie
CREATE TABLE periode_paie (
    id SERIAL PRIMARY KEY,
    mois INT NOT NULL,
    annee INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    statut int REFERENCES status_general(id),  -- ouverte, fermée
    admin_id INT REFERENCES admin(id),
    date_generation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (mois, annee)
);

-- Table principale de la paie d’un employé
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
    statut int REFERENCES status_general(id),  -- en_cours, payé
    admin_id INT REFERENCES admin(id) , 
    UNIQUE (employee_id, periode_id)
);

-- Détail des composants de la paie (heures sup, primes, déductions, etc.)
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





















--------------------- iLAY TALOHA ANSOUE -----------------------
create table annonce(
    id serial primary key ,
    title varchar(100),
    departement_id int REFERENCES departement(id) ,
    niveau_etude_id int REFERENCES niveau_etude(id) ,
    genre_id int REFERENCES genre(id) ,
    type_contrat_id int REFERENCES type_contrat(id) ,
    age_requis int  , 
    experience_requis int  , 
    salaire decimal(10,2) ,
    description text,
    date_publication date not null default current_date,
    date_expiration date , 
    status_id int REFERENCES status_general(id)
);

create table candidature ( 
    id serial primary key ,
    user_id int REFERENCES Utilisateur(id) ,
    annonce_id int REFERENCES annonce(id) ,
    date_candidature date not null default current_date,
    passed_test boolean default false ,
    entretien_planifie boolean default false ,
    status_traitement_id int REFERENCES status_traitement(id) 
) ;

create table detail_candidature (
    id serial primary key ,
    candidature_id int REFERENCES candidature(id) ,
    filiere_id int REFERENCES filiere(id) ,
    niveau_etude_id int REFERENCES niveau_etude(id) ,
    genre_id int REFERENCES genre(id) , 
    duree_experience varchar(250) , 
    skills text  ,
    motivation text , 
    experience_professionnelle text , 
    address VARCHAR(250) , 
    cv varchar(255) , 
    lm varchar(255) , 
    cin varchar(255) , 
    residence varchar(255)  , 
    date_naissance date  
) ;

create table candidat_to_employee(
    id serial primary key , 
    cand_id int REFERENCES Candidature(id) , 
    emp_id int REFERENCES Employee(id) , 
    date_validation  date
) ; 
 
create table qcm_question (
    id serial primary key , 
    departement_id int REFERENCES departement(id) ,
    question text  
) ;

create table qcm_reponse (
    id serial primary key , 
    question_id int REFERENCES qcm_question(id) ,
    reponse text  , 
    status_general boolean -- 0 false  , 1 true
) ;

create table qcm_test (
    id serial primary key , 
    candidature_id int REFERENCES candidature(id) ,
    score decimal(10,2) ,
    date_test timestamp default current_timestamp
) ;


create table planing_entretient (
    id serial primary key , 
    employee_id int REFERENCES employee(id) , -- Employee charger de l'entretient 
    candidature_id int REFERENCES candidature(id) , 
    siege_entreprise_id int REFERENCES siege_entreprise(id) 
) ;
 

create table resultat_entretient (
    id serial primary key ,
    employee_id int REFERENCES employee(id) ,
    candidature_id int REFERENCES candidature(id) ,
    pourcentage_satisfaction decimal(10,2) , 
    date_resultat timestamp default current_timestamp
) ;
 
create table scoring_candidature (
    id serial primary key , 
    candidature_id int REFERENCES candidature(id) ,
    pourcentage_embauche decimal(10,2) , 
    date_result at  timestamp default current_timestamp , 
    status_id int REFERENCES status_traitement(id)
 ) ;
 
create table signature_contrat (
    id serial primary key , 
    candidature_id int REFERENCES candidature(id), 
    status_id int REFERENCES status_traitement(id)
);  

create table parametre (
    id serial primary key , 
    name varchar(100)  , -- Recrutement auto
    is_active boolean default false 
) ;

create table detail_parametre (
    id serial primary key , 
    params_id int REFERENCES parametre(id) ,
    name varchar(100)  , 
    value int default 0 ,
    is_active boolean default false 
) ;
 
CREATE TABLE jour_ferie (
    id SERIAL PRIMARY KEY,
    name varchar(250)  ,
    date_ferie DATE  
);

CREATE TABLE horaire_travail (
    id SERIAL PRIMARY KEY,
    jour_semaine VARCHAR(20) NOT NULL -- Lundi, Mardi... 
); 

 
create table detail_horaire(
    id SERIAL PRIMARY KEY,
    id_horaire int REFERENCES horaire_travail(id) ,
    heure_debut TIME ,
    heure_fin TIME 
) ; 

CREATE TABLE emploi_dt_entretient (
    id SERIAL PRIMARY KEY,
    planing_entretient_id INT REFERENCES planing_entretient(id) ,
    tache_title VARCHAR(255) ,
    date_entretient DATE NOT NULL,
    heure_debut TIME NOT NULL,
    heure_fin TIME NOT NULL 
);


 

