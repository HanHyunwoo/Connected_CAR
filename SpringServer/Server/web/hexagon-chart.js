var hexagon = {
    bg_default_color: '#99ccff',
    value_default_color: '#ffff99',
    x_offset: 30,
    ssin: function(degree) {
        return Math.sin(degree * Math.PI / 180);
    },
    init: function(id, side_length, names, color) {
        this.side_length = side_length;
        this.hexagon = document.getElementById(id);
        this.hexagon.width = this.side_length * 2 * this.ssin(60) + this.x_offset * 2;
        this.hexagon.height = this.side_length * 2;

        var width = this.hexagon.width;
        var height = this.hexagon.height;
        var S = this.x_offset;

        if (typeof (color) === 'undefined') {
            color = this.bg_default_color;
        }

        hexagoncontext = this.hexagon.getContext('2d');
        hexagoncontext.fillStyle = color;
        hexagoncontext.strokeStyle = color;
        hexagoncontext.beginPath();
        hexagoncontext.moveTo(width / 2, 0);
        hexagoncontext.lineTo(width - S, height / 4);
        hexagoncontext.lineTo(width - S, height * 3 / 4);
        hexagoncontext.lineTo(width / 2, height);
        hexagoncontext.lineTo(S, height * 3 / 4);
        hexagoncontext.lineTo(S, height / 4);
        hexagoncontext.lineTo(width / 2, 0);
        hexagoncontext.stroke();
        hexagoncontext.fill();
        hexagoncontext.fillText(names[0], width / 2 + S / 2, 10);
        hexagoncontext.fillText(names[1], width - S, height / 4);
        hexagoncontext.fillText(names[2], width - S, height * 3 / 4);
        hexagoncontext.fillText(names[3], width / 2 + S / 2, height);
        hexagoncontext.fillText(names[4], 0, height * 3 / 4);
        hexagoncontext.fillText(names[5], 0, height / 4);
    },
    draw: function(values, color) {
        if (values.length < 6) {
            return false;
        }

        for (i in values) {
            values[i] = parseFloat(values[i]);

            if (values[i] > 1 || values[i] < 0) {
                return false;
            }
        }

        if (typeof (color) === 'undefined') {
            color = this.value_default_color;
        }

        var width = this.hexagon.width;
        var L = this.side_length;
        var S = this.x_offset;
        var V = values;

        hexagoncontext = this.hexagon.getContext('2d');
        hexagoncontext.fillStyle = color;
        hexagoncontext.strokeStyle = color;
        hexagoncontext.beginPath();
        hexagoncontext.moveTo(width / 2, L * (1 - V[0]));
        hexagoncontext.lineTo(this.ssin(60) * L * (1 + V[1]) + S, (1 - V[1] / 2) * L);
        hexagoncontext.lineTo(this.ssin(60) * L * (1 + V[2]) + S, (1 + V[2] / 2) * L);
        hexagoncontext.lineTo(width / 2, (1 + V[3]) * L);
        hexagoncontext.lineTo(this.ssin(60) * L * (1 - V[4]) + S, (1 + V[4] / 2) * L);
        hexagoncontext.lineTo(this.ssin(60) * L * (1 - V[5]) + S, (1 - V[5] / 2) * L);
        hexagoncontext.lineTo(width / 2, L * (1 - V[0]));
        hexagoncontext.stroke();
        hexagoncontext.fill();
    }
};