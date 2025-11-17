document.addEventListener('DOMContentLoaded', () => {

    const bouton = document.querySelector("#rechercher");
    const valiny = document.querySelector(".valiny");
    async function fetchData() {
        try {

            const code = document.querySelector('input[name="code"]').value;
            const date_debut = document.querySelector('input[name="date_debut"]').value;
            const date_fin = document.querySelector('input[name="date_fin"]').value;
            const tauxEmpMin = document.querySelector('input[name="taux_employe_min"]').value;
            const tauxEmpMax = document.querySelector('input[name="taux_employe_max"]').value;
            const tauxEmployeurMin = document.querySelector('input[name="taux_employeur_min"]').value;
            const tauxEmployeurMax = document.querySelector('input[name="taux_employeur_max"]').value;
            const actifCheckbox = document.querySelector('input[name="actif"]');
            const actif = actifCheckbox ? actifCheckbox.checked : null;

            const params = new URLSearchParams();
            params.append("code", code.trim());
            params.append("date_debut", date_debut);
            params.append("date_fin", date_fin);
            params.append("tauxEmpMin", tauxEmpMin);
            params.append("tauxEmpMax", tauxEmpMax);
            params.append("tauxEmployeurMin", tauxEmployeurMin);
            params.append("tauxEmployeurMax", tauxEmployeurMax);
            params.append("actif", actif);


            console.log("code ", params.get("code"));
            console.log(" date_debut ", params.get("date_debut"), " ", date_debut);
            console.log(" date_fin ", params.get("date_fin"), " ", date_fin);

            console.log(" taux employe min ", params.get("tauxEmpMin"));
            console.log(" taux employe max ", params.get("tauxEmpMax"));

            console.log(" taux employeur min ", params.get("tauxEmployeurMin"));
            console.log(" taux employeur max ", params.get("tauxEmployeurMax"));

            console.log("actif ", params.get("actif"));

            const response = await fetch('/parametre_taux_mamy/filter', {
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