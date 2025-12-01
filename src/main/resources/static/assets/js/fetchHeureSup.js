document.addEventListener('DOMContentLoaded', () => {

    const bouton = document.querySelector("#rechercher2");
    const valiny = document.querySelector(".valiny2");
    async function fetchData() {
        try {

            const empName = document.querySelector('input[name="employee2"]').value;
            const date_debut = document.querySelector('input[name="date_debut2"]').value;
            const date_fin = document.querySelector('input[name="date_fin2"]').value;
            const statusId = document.querySelector('select[name="status_id"]').value;
            const adminId = document.querySelector('select[name="admin_id"]').value;
            const typeCompensationId = document.querySelector('select[name="type_compensation_id"]').value;


            const params = new URLSearchParams();
            params.append("empName", empName.trim());
            params.append("date_debut", date_debut);
            params.append("date_fin", date_fin);
            params.append("statusId", statusId);
            params.append("adminId", adminId);
            params.append("typeCompensationId", typeCompensationId);


            console.log("empName ", params.get("empName"));
            console.log(" date_debut ", params.get("date_debut"), " ", date_debut);
            console.log(" date_fin ", params.get("date_fin"), " ", date_fin);   
            console.log(" methodeId ", params.get("methodeId"));
            console.log(" adminId ", params.get("adminId"));
            console.log(" typeCompensationId ", params.get("typeCompensationId"));


            const response = await fetch('/heure_supplementaire/filter', {
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