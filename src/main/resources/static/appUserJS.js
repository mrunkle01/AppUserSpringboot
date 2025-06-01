
async function getUsers() {
    const res = await fetch("/users");
    const users = await res.json();


    let sOut = "";

    users.forEach((user) => {
        sOut += `<div class="user">`;
        sOut += `<div class="name">Name: ${user.name}</div>`;
        sOut += `<div class="email">Email: ${user.email}</div>`;
        sOut += `<div class="id">ID: ${user.id}</div>`;
        sOut += `<div class="age">ID: ${user.age}</div>`;
        sOut += `</div>`;
    });

    document.getElementById("output").innerHTML = sOut;
}


async function addUser() {
    const user = {
        name: document.getElementById("nameInput").value,
        email: document.getElementById("emailInput").value,
        age: parseInt(document.getElementById("ageInput").value)
    };

    try {
        const res = await fetch("/user/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        if (res.ok) {
            alert("User added successfully!");
            await getUsers();
        } else {
            const errorText = await res.text();
            alert("Failed to add user: " + errorText);
        }
    } catch (error) {
        console.error("Error adding user:", error);
    }
}
async function deleteUser(){
    let email = document.getElementById("emailInput").value;

    try {
        const res = await fetch(`user/delete/${email}`,
            {
                method : "DELETE"
            });
        if (res.ok){
            alert("User Deleted Successfully")
            await getUsers();
        }
        else{
            const errorText = await res.text();
            alert("Failed to delete user: "+ errorText)
        }
    } catch (error){
        console.error("Error deleting user with email: ", error)
    }
}
function clear(){
    document.getElementById("output").innerHTML = " ";

}




