import AuthenticateUser from '../../mechanisms/authenticateTokens';
import {VendorSignUp, VendorLogin} from './vendorLoginSignup';
import { useRouteMatch, Switch, Route,Redirect} from 'react-router-dom'
import { useContext } from 'react';
import { UserContext } from '../../mechanisms/contexts';
import Orders from './ordersPage';
import { Link } from 'react-router-dom';
import { Menu } from './menu';
import {ModifyShop} from './shopmodifier';


function VendorHome(props){
    let {path, url} = useRouteMatch();
    return(
        <div className="VendorHome">
            <Switch>
              <Route path={`/vendor/login`} exact component={VendorLogin}/>
              <Route path={`${path}/signup`} exact component={VendorSignUp} />
              <Route path={`${path}/menu`} component={Menu}/>
              <Route path={`${path}/modifyshop`} component={ModifyShop}/>
              <Route path={path} exact component={DashBoard}/>
            </Switch>
        </div>
    );
}

function DashBoard(props){
    if(!AuthenticateUser()){
        return(
            <Redirect to={`/vendor/login/`}/>
        );
    }
    return (
        <div>
        <Selector/>
        <div className="w-75 mx-auto border border-dark"></div>
        <Orders/>
        </div>
    );
}


function Selector(){
    return(
        <div className="container my-3">
            <div className="row">
                <div className="col">
                    <Link to="/vendor/menu/" className="btn btn-secondary">See menu</Link>
                </div>
                <div className="col">
                    <Link to="/vendor/modifyshop/" className="btn btn-info">Set Shop Info</Link>
                </div>
            </div>
        </div>
    )
}


export default VendorHome;
