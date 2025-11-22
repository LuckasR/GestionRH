-- Table des intervalles avec gestion des limites infinies
-- Table des intervalles avec min et max optionnels
CREATE TABLE intervalle(
   id INT AUTO_INCREMENT,
   montant_min DECIMAL(19,4) NULL,  -- NULL = pas de limite inférieure
   montant_max DECIMAL(19,4) NULL,  -- NULL = pas de limite supérieure
   PRIMARY KEY(id),
   CONSTRAINT chk_au_moins_un_montant CHECK (
       montant_min IS NOT NULL OR montant_max IS NOT NULL
   ),
   CONSTRAINT chk_montants_valides CHECK (
       (montant_min IS NULL OR montant_max IS NULL OR montant_max > montant_min)
   )
);

-- Table des taux
CREATE TABLE taux(
   id INT AUTO_INCREMENT,
   taux_employe DECIMAL(15,2) NOT NULL,
   taux_employeur DECIMAL(15,2),
   PRIMARY KEY(id)
);

-- Table paramètre avec gestion temporelle
CREATE TABLE parametre_taux(
   id INT AUTO_INCREMENT,
   code VARCHAR(20) NOT NULL,
   description VARCHAR(50) NOT NULL,
   date_debut_application DATE DEFAULT CURRENT_DATE,
   date_fin DATE DEFAULT NULL,
   actif BOOLEAN NOT NULL,
   id_taux INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_taux) REFERENCES taux(id_taux),
   CONSTRAINT chk_dates_valides CHECK (date_fin_application > date_debut_application)
);

-- Table de liaison
CREATE TABLE taux_intervalle(
   id INT AUTO_INCREMENT,
   id_intervalle INT NOT NULL,
   id_parametre_taux INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_intervalle) REFERENCES intervalle(id),
   FOREIGN KEY(id_parametre_taux) REFERENCES parametre_taux(id),
   UNIQUE KEY uk_intervalle_parametre (id_intervalle, id_parametre_taux)
);