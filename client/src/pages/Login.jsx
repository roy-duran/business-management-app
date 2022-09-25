import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Redirect } from "react-router-dom";
import styled from "styled-components";
import LogoImg from "../assets/logo.png";
import {
  getCredentials,
  setPassword,
  setUser,
  setUsername,
} from "../reducers/rootReducer";
import { loginUser } from "../utils/requests";

const StyledLogin = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 4rem;
  padding-block: 4rem;
  align-items: center;
  & * {
    color: #1ba098;
    text-align: center;
  }
  & h1 {
    font-size: 3rem;
  }
  & h2 {
    font-size: 2rem;
  }
  & form {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    border: 3px solid #deb992;
    border-radius: 0.75rem;
    box-shadow: 4px 4px 10px 3px #cbcbcb7d;
    width: 30rem;
    gap: 3rem;
    padding-block: 4rem;
  }
  & img.logo {
    align-self: center;
    width: 10rem;
    aspect-ratio: 1 / 1;
    object-fit: contain;
    padding: 1rem;
    border-radius: 50%;
    background-color: #0d3a59;
    background-size: auto;
  }
  & input {
    align-self: center;
    width: 20rem;
    background-color: inherit;
    color: #1ba098;
    border: none;
    border-radius: 0;
    border-bottom: 3px solid white;
    :focus,
    :active {
      border-bottom: 3px solid #1ba098;
      outline: none;
    }
    ::placeholder,
    ::-webkit-input-placeholder,
    :-ms-input-placeholder {
      color: #1ba098;
    }
  }
  & input[type="submit"] {
    border: 2px solid #1ba098;
    width: auto;
    padding: 0.5rem 2rem;
    border-radius: 100rem;
    color: white;
    text-transform: uppercase;
    font-size: 0.8rem;
  }
  & p.error {
    color: red;
    height: 0;
    margin: 0;
  }
`;

const Login = () => {
  const dispatch = useDispatch();
  const credentials = useSelector(getCredentials);

  // Local State
  const [redirect, setRedirect] = useState("");
  const [error, setError] = useState("");

  const handleChange = (actionCreator) => (event) => {
    dispatch(actionCreator(event.target.value));
    setError("");
  };

  const handleLogin = async (event) => {
    event.preventDefault();
    const user = await loginUser(credentials);

    if (user) {
      dispatch(setUser(user));
      setRedirect(<Redirect to="/company" />);
    } else {
      setError("Invalid Username or Password.")
    }
  };

  return redirect ? (
    redirect
  ) : (
    <StyledLogin>
      <div>
        <h1>COOK SYSTEMS</h1>
        <h2>A FINAL APP</h2>
      </div>
      <form onSubmit={handleLogin}>
        <img src={LogoImg} alt="App Logo" className="logo" />
        <input
          type="text"
          placeholder="username"
          value={credentials.username}
          onChange={handleChange(setUsername)}
        />
        <input
          type="password"
          placeholder="password"
          value={credentials.password}
          onChange={handleChange(setPassword)}
        />
        <div>
          <input type="submit" value="Login" />
          <p className="error">{error}</p>
        </div>
      </form>
    </StyledLogin>
  );
};

export default Login;
