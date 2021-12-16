const yachtsList = document.getElementById('yachtList')
const searchBar = document.getElementById('searchInput')

const allYachts = [];

fetch("http://localhost:8080/yachts/api").then(response => response.json()).then(data => {
    for (let yacht of data) {
        allYachts.push(yacht);
    }
})

console.log(allYachts);

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredYachts = allYachts.filter(yacht => {
        return yacht.destination.name.toLowerCase().includes(searchingCharacters);

    });
    displayYachts(filteredYachts);
})


const displayYachts = (yachts) => {
    yachtsList.innerHTML = yachts
        .map((y) => {
            return ` <div class="col-md-3" >
                <div class="card mb-4 box-shadow">
                <img src="${y.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 225px; width: 100%; display: block;">
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom ">Model: ${y.model}</p>
                        <p class="card-text border-bottom ">Brand: ${y.brand}</p>
                        <p class="card-text border-bottom ">Destination: ${y.destination.name}</p>
                        <p class="card-text border-bottom ">Type: ${y.type}</p>
                        <p class="card-text border-bottom ">Price: ${y.price}</p>
                 
                    </div>
                    <div class="d-flex justify-content-between align-items-center">

                        <div class="btn-group">
                            <a href="/yachts/details/${y.id}"  type="button" class="btn btn-sm btn-outline-secondary">Details</a>
                        </div>
                        <div class="btn-group">
                            <a href="/yachts/delete/${y.id}"  type="button" class="btn btn-sm btn-outline-secondary">Delete</a>
                        </div>

                    </div>
                </div>
            </div>
            </div>`
        })
        .join('');

}
