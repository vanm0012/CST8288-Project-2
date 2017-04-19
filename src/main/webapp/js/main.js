var composer_index = 0;

// Onload function
window.onload = function()
{
    get_composers();
};

$('#btnNext').click(function(){
    composer_index++;
    get_composers();
});

$('#btnPrev').click(function(){
    if (composer_index == 0)
    {
        return;
    }
    composer_index--;
    get_composers();
});

function clear_rows()
{
    $('#composerTableBody').text("");
}

function add_row(composition, movements)
{
    // Adds a row to #composerTableBody
    // composition : name of the composition
    // movements : A list of the movements

    $('#composerTableBody').append('<tr><td>' +
    composition +
    '</td><td>' +
    movements +
    '</td></tr>');
}

function get_composers()
{
    clear_rows();

    $.post("symphony/composers", function(data)
    {
        if (composer_index == data.length)
        {
            composer_index--;
        }
        var composer = data[composer_index];
        $('#composerName').text(composer);
        get_compositions(composer);
    });
}

function get_compositions(composer)
{
    $.post("symphony/composers", {name: composer}, function(data)
    {
        console.log(data);

        for (composition in data)
        {
            add_row(composition, data[composition]);
        }
    });
}
