<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<html>
<head>
    <H2>SELECT RESOURCES FROM DROPDOWN</H2>

</head>
<body>
<table id="table" border="1">

    <tr>
        <td>ResourceId</td>
        <td>Quantity</td>
        <td>impactGWP100_kgCO2e</td>
        <td>impactAP_kgSO2e</td>
    </tr>
    <tr>
        <td><form id="form1"><input name="id" value="---" id="resource"  /></form></td>
        <td><input type="text" name="quantity" id="quantity"></td>
        <td>---</td>
        <td>---</td>
        <td><input form="form1" type="submit" value="Calculate" onclick="_getResult()" /></td>
    </tr>

</table>

<table id="table_total">

</table>
<div id="list"></div>
<h4 id="result"></h4>

</body>
<script>
    const URL="http://localhost:8070/api/";
    let resources_list=[];
    var final_resources_added=[];
    var temp=0;


    /*
     Select resources from dropdown
    */
    function _selectResources() {

        var resource_selected=document.getElementById("select").value;
        console.log(resource_selected);
        console.log(resources_list);
        temp=1;
        resources_list.forEach( function( resource ){
            if( resource.name === resource_selected ){
                console.log("found");
                console.log(resource.resourceId);
                _changeForm(resource.resourceId,resource.impactAP_kgSO2e,resource.impactGWP100_kgCO2e);
            }
        } );
    }


    /*
    Changes the data in table according to resource selected
    */

    function _changeForm(resourceId,impactAP_kgSO2e,impactGWP100_kgCO2) {

        this.resourceid=resourceId;
        this.impactGWP100_kgCO2e=impactGWP100_kgCO2;
        this.impactAP_kgSO2e=impactAP_kgSO2e;

        var myTable = document.getElementById('table');
        myTable.rows[1].cells[0].innerHTML = resourceId;
        myTable.rows[1].cells[0].style.color="blue"
        //myTable.rows[1].cells[1].innerHTML = resourceId;
        myTable.rows[1].cells[2].innerHTML = impactGWP100_kgCO2e;
        myTable.rows[1].cells[3].innerHTML = impactAP_kgSO2e;
        myTable.rows[1].cells[2].style.color="blue"
        myTable.rows[1].cells[3].style.color="blue"

    }

    /*
    Fetch Resources list from database and display it
    */

    async function _showAll() {

        document.getElementById("result").remove();
        document.getElementById("list").remove();
        document.getElementById("table_total").remove();
        const table_result = document.createElement("table");

        table_result.id="table_total"
        table_result.style.borderSpacing = "15px"
        var row = table_result.insertRow(0);
        row.insertCell(0).innerHTML="ResourceName";
        row.insertCell(1).innerHTML="impactGWP100_kgCO2e";
        row.insertCell(2).innerHTML="impactAP_kgSO2e";
        row.insertCell(3).innerHTML="quantity";
        row.insertCell(4).innerHTML="total_impactGWP100_kgCO2e";
        row.insertCell(5).innerHTML="total_impactAP_kgSO2";
        row.insertCell(6).innerHTML="Total_impact";
        row.style.color="Black";
        table_result.style.border = "1px solid #000";
        table_result.style.borderWidth = "1px";
        table_result.style.color="Brown";
        var row_number=1

        /*Using GET method to retrieve data from database*/
        const response = await fetch(URL+"resources", {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            //body: JSON.stringify(resources_data_add),
        });
        response.json().then(data => {
            console.log('Success:', data);
            data.forEach(resource => {
                console.log(resource);
                console.log(resource.name);
                var row = table_result.insertRow(row_number);
                row.insertCell(0).innerHTML=resource.name;
                row.insertCell(1).innerHTML=resource.impactGWP100_kgCO2e;
                row.insertCell(2).innerHTML=resource.impactAP_kgSO2e;
                row.insertCell(3).innerHTML=resource.quantity;
                row.insertCell(4).innerHTML=resource.total_impactGWP100_kgCO2e;
                row.insertCell(5).innerHTML=resource.total_impactAP_kgSO2e;
                row.insertCell(6).innerHTML=resource.total_impact;
                row_number++;
                }
            )

           // document.body.appendChild(document.createTextNode(data.toString()));
        }).catch((error) => {
            alert("Error..Please try again..No data present");
            console.error('Error:', error);
        });
        const _result=document.createElement("h4")
        _result.id="result";
        _result.innerHTML="Results";
        document.body.appendChild(_result);
        document.body.appendChild(table_result);
        _getTotal();

    }

    function _checkValidations(quantity)
    {

        console.log("select length");
        console.log(document.getElementById('select').options.length );
        console.log(typeof quantity);
        if (quantity == "")
        {
            alert("Please input quantity");
            return false;
        }
        if(temp==0)
        {
            alert("Please select resources");
        }
        return true;

    }

    async function _getResult() {

        var quantity=document.getElementById('quantity').value;
        console.log(quantity);
        var res;

        var x = document.getElementById("select").value;
        console.log("select value of options "+x);
        if(_checkValidations(quantity)) {
            console.log(resources_list);
            var impactAP_kgSO2e, impactGWP100_kgCO2e;
            resources_list.forEach(function (resource) {
                if (resource.name === x) {
                    res = resource;
                    impactAP_kgSO2e = resource.impactAP_kgSO2e;
                    impactGWP100_kgCO2e = resource.impactGWP100_kgCO2e;
                }
            });

            let resources_data_add = {
                "name": res.name,
                "impactAP_kgSO2e": impactAP_kgSO2e,
                "impactGWP100_kgCO2e": impactGWP100_kgCO2e,
                "resourceId": res.resourceId,
                "quantity": quantity,
                "impactAP_kgSO2_total": 0,
                "impactGWP100_kgCO2e_total": 0,
            }

            const response = await fetch(URL+"resources", {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(resources_data_add),
            });

            response.json().then(data => {
                console.log('Success:', data);
                alert("Data Submitted");
                // document.body.appendChild(document.createTextNode(data));
            }).catch((error) => {
                alert("Error..Please try again");
                console.error('Error:', error);
            });


            final_resources_added.push(resources_data_add);
            console.log(resources_data_add);
            console.log(final_resources_added);

        }

        else
        {
            console.log("validations failed");
        }

    }


  /* Get the total calculations and add the result on the page*/
    async function _getTotal() {

        var total=document.createElement("div");
        total.id="list";
        total.style.color="brown";

        const response = await fetch(URL+"resources/total", {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },

        });
        response.json().then(data => {
            console.log('Success:', data[0]);
           total.innerHTML="the value of Total is "+data[0]+",          "+"the value of total_ImpactGWP100_kgCO is = "+data[1]+",            "+"the value of total_ImpactAP_kgSO2e is = "+data[2];
            document.body.appendChild(total);

        }).catch((error) => {
            console.error('Error:', error);
        });

    }


/*Load the configuration.json file and add the data*/
    function _fetchData() {

        fetch("http://localhost:8070/configuration.json")
            .then(response => {
                return response.json();
            })
            .then(data => {
                _resourcesConfig(data);
                console.log(data);
                return data;
            }).catch((error) => {
            console.error('Error:', error);
        });

    }


    _fetchData();

    let list_resources=[];



/* Populate the resources in dropdown list */
    function _resourcesConfig(data)
    {
        console.log(data);
        var arr = [];
        Object.keys(data).forEach((key) => {
            arr.push({[key]: data[key]});
        });
        console.log(arr);

        var _select = document.createElement("select");
        _select.id='select';
        _select.style.color="brown";
        _select.style.background="lightblue";
        _select.style.width="300px";
        _select.style.height="25px";

        for(let k=0;k<(arr[0].resources).length;k++)
        {
            _select.options[_select.options.length] = new Option(arr[0].resources[k].name,arr[0].resources[k].name);
            let resources_data = {
                "name":arr[0].resources[k].name ,
                "impactAP_kgSO2e": arr[0].resources[k].impacts[0].impactAP_kgSO2e,
                "impactGWP100_kgCO2e": arr[0].resources[k].impacts[0].impactGWP100_kgCO2e,
                "resourceId": arr[0].resources[k].resourceId,
            }
            console.log(resources_data);
            resources_list.push(resources_data);
            console.log(resources_list);
        }
        _select.addEventListener(
            'change',
            _selectResources,
            false
        );

        console.log("calculations json ");
        for(let k=0;k<(arr[1].calculationRules).length;k++)
        {

            console.log(arr[1].calculationRules[k].name);

        }
        //document.querySelector('#select').insertAdjacentHTML('afterend',_select);
        document.body.appendChild(_select);
        var btn = document.createElement("BUTTON");
        btn.id="show";
        btn.innerHTML = "showAll";
        btn.type = "submit";
        btn.name = "formBtn";
        btn.addEventListener(
            'click',
            _showAll
        );

        document.body.appendChild(btn);

    }
</script>

</html>
</body>
</html>
