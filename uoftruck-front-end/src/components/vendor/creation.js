import axios from "axios";
import { useState, useContext} from "react";
import { Redirect } from "react-router";
import { domain, port} from './../../mechanisms/contexts'
import {GetToken} from './../../mechanisms/authenticateTokens';

function SingletonCreation(data,arr, event, setResp,token){
    event.preventDefault();
    let finalDat = {};
    finalDat['name'] = data.name;
    finalDat['price'] = data.price;
    finalDat['description'] = data.description;
    finalDat['name'] = data.name;
    let store = {};
    for (var i = 0; i < arr.length; i++) {
        store[data['selection'][`addon${i}`]] = data['selection'][`quantity${i}`];
    }   
    let allowedTypes = [];
    for (let dat of data['acceptedTypes']) {
        allowedTypes.push(dat.value);
    }
    finalDat["defaultSelection"] = store;
    finalDat["allowedAddonTypes"] = allowedTypes;
    finalDat["isAvailable"] = true;
    finalDat["id"] = "N/A";
    
    axios.post(`${domain}:${port}/CreateSingleton/${token}`,finalDat).then((resp)=>{
        console.log(resp);
    });
}

function increment(original, setter){
    var random = original.data;
    random.push(original.counter);
    setter({data:random, counter: original.counter+1});
}

function remove(original, setter, i){
    if(original.data.length > 1){
        let random = original.data
        var index = random.indexOf(i);
        random.splice(index, 1); 
        setter({data:random});  
    }
}


function generateOptions(data,form, setForm){
    //TODO: THIS MAY CAUSE ISSUES IN THE FUTURE, THE WAY THE DATA IS STORRED THIS NEEDS debugging.
    return(
    data.map((i,index)=>{
        return(
        <div class="input-group" key={index}>
            <select name="" onInput={e=>setForm(
                {...form,selection:{...form.selection,[`addon${index}`]:e.target.value}}
                )} id={`addon.${index}`} className="form-control rounded-0">
                <option value="">sdafa</option>
                <option value="">fx</option>
                <option value="">adf</option>
                <option value="">zxc</option>
            </select>
            <input type="number" onInput={e=>setForm(
                {...form,selection:{...form.selection,[`quantity${index}`]:e.target.value}}
                )} class="form-control rounded-0" aria-label="Quantity" min="1" id={`quantity.${index}`}/>
        </div>
        );
    })
    );
}

function showError(result){
    if(result === null){
        return (
            <div className="errorContainer"></div>
        )
    }
    if(result.status !== 200){
        return (
            <div className="errorContainer text-danger"><small>result.message</small></div>
        )
    }
    return(
        <Redirect to="/vendor/menu/"/>
    )
}


export function CreateSingleton(){
    const [result, setResult] = useState(null);
    const [options, setOptions]  = useState({data:[1],counter:2});
    const [formData, setFormData] = useState({});
    const token = GetToken();
    return(
        <div className="container my-3">
            <div className="display-5">Create Singleton</div>
                {showError(result)}
                <form onSubmit={(event) => {SingletonCreation(formData, options.data, event, setResult, token)}}>
                    <div className="form-group my-2">
                        <label for="name">Name:</label>
                        <input key="name" onInput={e=>setFormData({...formData,name:e.target.value})} type="text" className="form-control my-2" id="name"/>
                    </div>
                    <div className="my-2"></div>
                    <div className="form-group">
                        <label for="price">Price:</label>
                        <input key="priceInput" onInput={e=>setFormData({...formData,price:e.target.value})} type="number" className="form-control my-2" min="1" id="price"/>
                    </div>
                    <div className="form-group my-3">
                        <label for="location">Description:</label>
                        <textarea class="form-control" onInput={e=>setFormData({...formData,description:e.target.value})} aria-label="Description" id="description"></textarea>
                    </div>
                    <div className="form-group">
                        <label for="acceptedAddons">Accepted types of Addon(s):</label>
                        <select onInput={e=>setFormData({...formData,acceptedTypes:e.target.selectedOptions})} multiple class="form-control my-2" id="acceptedAddons">
                            <option value="cond">Condiments</option>
                            <option value="sad">alsdkj</option>
                            <option value="123">asdlfkj</option>
                            <option value="123jadks">asdfmndsa</option>
                        </select>
                    </div>
                    <div className="my-3">
                        <label for="acceptedAddons">Default Addon Selections:</label>
                        <div className="row">
                            <div className="col text-muted">
                                <small>Addon</small>
                            </div>
                            <div className="col text-muted">
                                <small>Quantity</small>
                            </div>
                        </div>
                        <div className="my-2"></div>
                        {generateOptions(options.data, formData, setFormData)}
                        <div className="addbtn my-0">
                            <button type='button' onClick={()=>{increment(options, setOptions)}} className="btn btn-secondary w-100 rounded-0">Add Addon</button>
                            <button type="button" className="btn btn-danger w-100 rounded-0" onClick={()=>{remove(options, setOptions,1)}}>Remove One Addon</button>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary w-50">Submit </button>
                </form>

                   
        </div>
);
}



function runCreateAddon(event, data, setResponse){
    event.preventDefault();
    let finalDat = {};
    finalDat['name'] = data.name;
    finalDat['price'] = data.price;
    let allowedTypes = [];
    for (let dat of data['types']) {
        allowedTypes.push(dat.value);
    }
    finalDat["addonTypes"] = allowedTypes;
    finalDat["isAvailable"] = true;
    finalDat["id"] = "N/A";

    axios.post(`${domain}:${port}/CreateSingleton/${token}`,finalDat).then((resp)=>{
        console.log(resp);
    });

}

export function CreateAddon(){
    const [data, setData] = useState({});
    const [response, setResponse] = useState(null);
    return(
            <div className="container my-3">
                <div className="display-5">Create Addon</div>
                <form >
                    <div className="form-group">
                        <label for="name">Name:</label>
                        <input key="name" onInput={e=>setData({...data,name:e.target.value})} type="text" className="form-control my-2" id="name"/>
                    </div>
                    <div className="my-2"></div>
                    <div className="form-group">
                        <label for="price">Price:</label>
                        <input key="priceInput" onInput={e=>setData({...data,price:e.target.value})} type="number" className="form-control my-2" min="1" id="price"/>
                    </div>
                    <div className="form-group">
                        <label for="price" onInput={e=>setData({...data,types:e.target.selectedOptions})}>Type of Addon:</label>
                        <select multiple class="form-control my-2" id="sel1">
                            <option>Condiments</option>
                            <option>alsdkj</option>
                            <option>asdlfkj</option>
                            <option>asdfmndsa</option>
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary w-50">Submit </button>
                </form>
            </div>
    );
}


export function CreateFood(){
    return(
        <div className="container my-3">
                <div className="display-5">Create Food</div>
                <form >
                    <div className="form-group my-3">
                        <label for="name">Name:</label>
                        <input key="nameInput" type="text" className="form-control my-2" id="name"/>
                    </div>
                    <div className="my-2"></div>
                    <div className="form-group my-3">
                        <label for="location">Description:</label>
                        <textarea class="form-control" aria-label="Description" id="description"></textarea>
                    </div>
                    <div className="form-group my-3">
                        <label for="components">Components:</label>
                        <select multiple class="form-control my-2" id="components">
                            <option>Burger</option>
                            <option>Asadsa</option>
                            <option>asdfkjads</option>
                            <option>zcxv</option>
                            <option>zcsadfxv</option>
                            <option>zcsadfadsfxv</option>
                            <option>zcsadfxv</option>
                        </select>
                    </div>
                    <div className="form-group my-3">
                        <label for="price">Price:</label>
                        <div className="w-100"></div>
                        <small className="text-muted text-left">Enter -1 for autocalculation</small>
                        <input key="priceInput" type="number" className="form-control my-2" min="-1" id="price"/>
                    </div>
                    <button type="submit" className="btn btn-primary w-50">Submit </button>
                </form>
            </div>
);
}