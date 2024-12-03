const setExamplePart1 = () => {
    document.getElementById("input").value = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
}

const setExamplePart2 = () => {
    document.getElementById("input").value = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
}

const processDay3 = () => {
    const alwaysEnabled = !document.getElementById("part1").checked
    let enabled = true
    const processValue = (v) => {
        let result = 0;
        if (v === "do()") {
            enabled = true;
        } else if (v === "don't()") {
            enabled = alwaysEnabled || false;
        } else if (enabled) {
            const values = v.substring(4, v.length - 1).split(",")
            result = values[0] * values[1];
        }
        return result;
    }

    const input = document.getElementById("input").value
    const matches = [...input.matchAll(/do\(\)|don't\(\)|mul\(\d+,\d+\)/g)];
    const result = matches
        .map(v => processValue(v[0]))
        .reduce((a,v)=>a+v, 0)
    document.getElementById("result").textContent = result.toString();
}