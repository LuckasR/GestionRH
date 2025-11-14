create table departement (
    id serial primary key , 
    name varchar(100) 
) ;

create table genre (
    id serial primary key , 
    name varchar(100) 
) ;

create table niveau_etude (
    id serial primary key , 
    name varchar(100) 
) ;
 
create table type_contrat (
    id serial primary key , 
    name varchar(100) , 
    durre_contrat decimal(10,2)  , 
    recurrence_renouvelement int 
) ;

create table status( 
    id serial primary key , 
    name varchar(100) 
) ; 

create table status_traitement( 
    id serial primary key , 
    name varchar(100) 
) ; 

create table role (
    id serial primary key , 
    name varchar(100)  
) ; 

--Cnaps, Ostie
create table organisme_social (
    id serial primary key , 
    name varchar(100)   , 
    pourcentage decimal(5,2)
) ; 
 
create table societe (
  id serial primary key , 
  name varchar(100) ,
  nombre_qcm_test int default 1 ,
  date_creation timestamp  default current_timestamp
); 
 

 -- Begin Gestion personnel
create table admin  (
    id serial primary key , 
    username varchar(100) , 
    password varchar(100)
) ;

create table employee  (
    id serial primary key , 
    role_id int references role(id) ,
    username varchar(100) , 
    password varchar(100)
) ; 


create table utilisateur  (
    id serial primary key , 
    username varchar(100) , 
    password varchar(100)
) ; 


create table contrat_employee  (
    id serial primary key , 
    admin_id int references admin(id) ,     
    employee_id int references employee(id) ,
    contrat_id int references type_contrat(id) , 
    date_debut_contrat timestamp not null default current_timestamp  , 
    date_fin_contrat timestamp not null default current_timestamp  
) ; 

create table information_employee (
    id serial primary key , 
    employee_id int references employee(id) ,
    contrat_id int references type_contrat(id) , 
    first_name varchar(100) , 
    last_name varchar(100) , 
    email varchar(100) , 
    phone varchar(20) , 
    address VARCHAR(200) , 
    cv varchar(255) , 
    lm varchar(255) , 
    cin varchar(255) , 
    residence varchar(255) , 
    date_creation timestamp not null default current_timestamp
) ;


create table assurance_social_employee(
    id serial primary key ,
    employee_id int references employee(id) ,
    organisme_id int references organisme_social(id) ,
    date_attribution timestamp not null default current_timestamp
) ; 
 
create table historique_role (
    id serial primary key , 
    user_id int references Utilisateur(id) ,
    role_id int references role(id) ,
    date_modification timestamp not null default current_timestamp
) ;

-- End  Gestion personnel


create table annonce(
    id serial primary key ,
    title varchar(100),
    departement_id int references departement(id) ,
    niveau_etude_id int references niveau_etude(id) ,
    genre_id int references genre(id) ,
    type_contrat_id int references type_contrat(id) ,
    description text,
    date_publication date not null default current_date,
    date_expiration date , 
    status_id int references status(id)
);

create table candidature( 
    id serial primary key ,
    user_id int references Utilisateur(id) ,
    annonce_id int references annonce(id) ,
    date_candidature date not null default current_date,
    status_traitement_id int references status_traitement(id)
); 

create table detail_candidature (
    id serial primary key ,
    candidature_id int references candidature(id) ,
    type_contrat_id int references type_contrat(id) ,
    duree_experience varchar(100) , 
    genre_id int references genre(id) , 
    skills text  ,
    motivation text , 
    experience_professionnelle text , 
    address VARCHAR(250) , 
    cv varchar(255) , 
    lm varchar(255) , 
    cin varchar(255) , 
    residence varchar(255) 
);

create table qcm_question (
    id serial primary key , 
    departement_id int references departement(id) ,
    question text  
);

create table qcm_reponse (
    id serial primary key , 
    question_id int references qcm_question(id) ,
    reponse text  , 
    status boolean -- 0 false  , 1 true
);

create table qcm_test (
    id serial primary key , 
    candidature_id int references candidature(id) ,
    score decimal(10,2) ,
    date_test timestamp default current_timestamp
);


create table planing_entretient(
    id serial primary key , 
    employee_id int references employee(id) ,
    candidature_id int references candidature(id) ,
    date_entretient timestamp not null , 
    lieu varchar(200)
);

create table resultat_entretient (
    id serial primary key ,
    employee_id int references employee(id) ,
    candidature_id int references candidature(id) ,
    pourcentage_satisfaction decimal(10,2) , 
    date_resultat  timestamp default current_timestamp
);



create table scoring_candidature (
    id serial primary key , 
    candidature_id int references candidature(id) ,
    pourcentage_embauche decimal(10,2) , 
    date_resultat  timestamp default current_timestamp
);



 