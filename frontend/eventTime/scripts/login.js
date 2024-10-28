document.querySelector('form').addEventListener('submit', async (event) => {
    event.preventDefault(); // Formun otomatik gönderimini durdur

    // Giriş bilgilerini al
    const email = document.querySelector('input[type="text"]').value;
    const password = document.querySelector('input[type="password"]').value;

    // API isteği için body
    const requestBody = {
        email: email,
        password: password
    };

    try {
        // API isteği
        const response = await fetch('http://127.0.0.1:8080/api/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        if (response.ok) {
            const data = await response.json();
            sessionStorage.setItem('userName', data.name);
            sessionStorage.setItem('userId', data.id); // Kullanıcının ID'sini al
            sessionStorage.setItem('userEmail', data.email); // Kullanıcının ID'sini al
            window.location.href = 'home.html';
        } else if (response.status === 401) {
            // 401 hatası durumunda
            alert('Kullanıcı adı veya şifre hatalı.');
        } else {
            // Diğer hata durumları için genel bir mesaj
            const errorData = await response.json();
            console.log('Hata mesajı:', errorData);
            alert('Bir hata oluştu: ' + (errorData.message || 'Hata mesajı bulunamadı.'));
        }
        
    } catch (error) {
        console.error('Giriş sırasında bir hata oluştu:', error);
        alert('Bir hata oluştu, lütfen tekrar deneyin.');
    }
});
