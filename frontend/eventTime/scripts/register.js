document.getElementById('register-form').addEventListener('submit', async (event) => {
    event.preventDefault(); // Formun otomatik gönderimini durdur

    // Kayıt bilgilerini al
    const name = document.getElementById('name').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // API isteği için body
    const requestBody = {
        name: name,
        email: username,
        password: password
    };

    try {
        // API isteği
        const response = await fetch('http://127.0.0.1:8080/api/users/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        if (response.ok) {
            // Başarılı kayıt durumunda login sayfasına yönlendirme
            window.location.href = 'login.html';
        } else {
            // Hatalı durum için mesaj gösterme
            const errorData = await response.json();
            alert('Kayıt sırasında bir hata oluştu: ' + (errorData.message || 'Hata mesajı bulunamadı.'));
        }
        
    } catch (error) {
        console.error('Kayıt sırasında bir hata oluştu:', error);
        alert('Kayıt sırasında bir hata oluştu, lütfen tekrar deneyin.');
    }
});
