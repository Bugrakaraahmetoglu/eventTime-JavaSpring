// Kullanıcı adını sessionStorage'dan al ve göster
const userName = sessionStorage.getItem('userName');
document.getElementById('user-name').textContent = userName ? userName : 'Misafir';

// API isteği atma ve verileri işleme
fetch("http://127.0.0.1:8080/api/post/getAllPosts")
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // ID'ye göre azalan sıralama
        data.sort((a, b) => b.id - a.id);

        const container = document.getElementById('card-container');

        // Her bir post için bir kart oluşturma
        data.forEach(post => {
            const card = document.createElement('div');
            card.className = 'card custom-card shadow mb-4';

            // Kartın içeriğini oluşturma, görsel mevcutsa gösterme
            const imageHTML = post.image ? `<img src="${post.image}" alt="Post Image" style="width: 120px; height: 150px;" class="mb-2">` : '';

            card.innerHTML = `
                <div class="card-body">
                   
                    <h5 class="card-title">${post.userName}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Date: ${post.eventTime} | ${post.cityName}</h6>
                    <p class="card-text">
                        ${post.content}<br><br>
                         ${imageHTML}
                    </p>
                </div>
            `;

            // Kartı konteynıra ekleme
            container.appendChild(card);
        });
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
