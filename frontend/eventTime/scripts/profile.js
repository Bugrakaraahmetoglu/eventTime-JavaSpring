document.getElementById("logoutButton").addEventListener("click", async function() {
    try {
        const response = await fetch("http://127.0.0.1:8080/api/users/logout", {
            method: "POST", // Çıkış işlemi için genellikle POST metodu kullanılır
            credentials: "include" // Gerekirse cookie'leri göndermek için
        });

        if (response.ok) {
            // Başarılı çıkış yapıldığında login.html sayfasına yönlendir
            window.location.href = "login.html";
        } else {
            const errorData = await response.json();
            alert("Çıkış yaparken bir hata oluştu: " + errorData.message);
        }
    } catch (error) {
        alert("Bir hata oluştu: " + error.message);
    }
});
