let raster = document.getElementById("paintRaster")
let barva = document.getElementById("color")

let kresleni = false

raster.style.display = "grid"
raster.style.gridTemplateColumns = "repeat(20, 20px)"
raster.style.gap = "1px"

document.addEventListener("mousedown", function () {
    kresleni = true
})

document.addEventListener("mouseup", function () {
    kresleni = false
})

for (let i = 0; i < 400; i++) {
    let pixel = document.createElement("div")

    pixel.style.width = "20px"
    pixel.style.height = "20px"
    pixel.style.backgroundColor = "white"
    pixel.style.border = "1px solid #ccc"

    pixel.addEventListener("mousedown", function () {
        pixel.style.backgroundColor = barva.value
    })

    pixel.addEventListener("mouseover", function () {
        if (kresleni) {
            pixel.style.backgroundColor = barva.value
        }
    })

    raster.appendChild(pixel)
}