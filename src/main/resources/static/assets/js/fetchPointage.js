document.addEventListener('DOMContentLoaded', () => {

    const bouton = document.querySelector("#rechercher");
    const valiny = document.querySelector(".valiny");
    async function fetchData() {
        try {

            const empName = document.querySelector('input[name="employee"]').value;
            const date_debut = document.querySelector('input[name="date_debut"]').value;
            const date_fin = document.querySelector('input[name="date_fin"]').value;
            const methodeId = document.querySelector('select[name="methode_id"]').value;
            const heure_arrivee  = document.querySelector('input[name="heure_arrivee"]').value;
            const heure_depart  = document.querySelector('input[name="heure_depart"]').value;

            const params = new URLSearchParams();
            params.append("empName", empName.trim());
            params.append("date_debut", date_debut);
            params.append("date_fin", date_fin);
            params.append("methodeId", methodeId);
            params.append("heure_arrivee", heure_arrivee);
            params.append("heure_depart", heure_depart);


            console.log("empName ", params.get("empName"));
            console.log(" date_debut ", params.get("date_debut"), " ", date_debut);
            console.log(" date_fin ", params.get("date_fin"), " ", date_fin);   
            console.log(" methodeId ", params.get("methodeId"));
            console.log(" heure_arrivee ", params.get("heure_arrivee"));
            console.log(" heure_depart ", params.get("heure_depart"));


            const response = await fetch('/pointage/filter', {
                method: 'POST',
                headers: { 'Content-type': 'application/x-www-form-urlencoded' },
                body: params
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }


            const datum = await response.text();
                        console.log(" datum ",datum);

            valiny.innerHTML = datum;
        } catch (error) {
            console.log("erreur lors de fetch ", error);
        }
    }


    bouton.addEventListener('click', async function () {
        await fetchData();
    });



});