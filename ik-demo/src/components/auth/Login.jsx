import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import "../../style/css/auth.css"
import {useAuth} from "./AuthContext";

function Login() {

    const {login} = useAuth();

    const handleLogin = (event) => {
        event.preventDefault();
        login({
            username: event.target[0].value,
            password: event.target[1].value
        })
    }

    return (
        <div>
            <Form id="main" onSubmit={handleLogin}>
                <h2 className="form-title">Login to your account</h2>

                <FormGroup floating className="input-parent">
                    <Input id="username" name="username" placeholder="Username" required/>
                    <Label for="username" className="input-label">Username</Label>
                </FormGroup>
                {' '}
                <FormGroup floating className="input-parent">
                    <Input id="password" name="password" placeholder="Password" type="password" required/>
                    <Label for="password" className="input-label">Password</Label>
                </FormGroup>
                {' '}
                <Button type="submit" className="submit-button">Submit</Button>
            </Form>

        </div>
    );
}

export default Login;