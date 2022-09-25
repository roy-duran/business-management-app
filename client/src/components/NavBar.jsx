import styled from "styled-components";
import { NavLink, Redirect, useLocation } from "react-router-dom";

import HamburgerImg from "../assets/hamburger.svg";
import LogoImg from "../assets/logo.png";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  eraseSession,
  getAdmin,
  getCompany,
  getCredentials,
  getUser,
  setTeam,
} from "../reducers/rootReducer";

const NothingSpace = styled.nav`
  height: 4rem;
  position: static;
`;

const StyledNav = styled.nav`
  z-index: 5;
  position: fixed;
  top: 0;
  width: 100%;
  background: #051622;
  display: flex;
  align-items: center;
  padding: 0.5rem;
  gap: 1rem;
  border: 0.0625rem solid #deb992;
  > * {
    z-index: 5;
  }
  #logo {
    width: 3rem;
    border-radius: 50%;
    aspect-ratio: 1 / 1;
    object-fit: contain;
    background: #214963;
  }
  #warning {
    text-transform: uppercase;
    color: #f24e1e;
    margin: 0;
  }
  #menu-button {
    background: #0d3a59;
    border-radius: 0.5rem;
    border: none;
    width: 3rem;
    aspect-ratio: 1 / 1;
    margin-left: auto;
    padding: 0.5rem;
    cursor: pointer;
  }
  ul#links {
    position: absolute;
    top: 0;
    left: 0rem;
    display: ${({ showLinks }) => (showLinks ? "flex" : "none")};
    flex-direction: column;
    width: 100%;
    padding: 0;
    margin: 0;
    li {
      margin: 0;
      border-bottom: 0.125rem solid #1ba098;
      :last-child {
        border: 0;
      }
      a {
        height: 4rem;
        display: grid;
        place-content: center;
        margin: 0;
        background: #214963;
        color: #1ba098;
        font-size: 2rem;
        text-decoration: none;
      }
    }
  }
  div.overlay {
    display: ${({ showLinks }) => (showLinks ? "inherit" : "none")};
    position: absolute;
    top: 0;
    right: 0;
    width: 100vw;
    height: 99vh;
    z-index: 4;
    background: #0008;
  }
`;

const NavBar = () => {
  const dispatch = useDispatch();
  const credentials = useSelector(getCredentials);
  const user = useSelector(getUser);
  const company = useSelector(getCompany);
  const isAdmin = useSelector(getAdmin);
  const { pathname } = useLocation();

  // Local State
  const [showing, updateShowing] = useState(false);
  const [redirect, setRedirect] = useState("");

  const logout = (event) => {
    event?.preventDefault();
    setRedirect(<Redirect to="" />);
    dispatch(eraseSession());
  };

  const toggleShowing = (event) => updateShowing(!showing);

  // Handle Redirects
  if (!redirect && (!credentials || !user)) logout();
  if (!redirect && !company) setRedirect(<Redirect to="/company" />);
  if (!redirect && !isAdmin && new Set(["/teams", "/users"]).has(pathname))
    setRedirect(<Redirect to="/announcements" />);

  const links = [
    { display: "Select Company", admin: true, link: "/company" },
    { display: "Announcements" },
    { display: "Teams", admin: true },
    { display: "Projects", admin: false },
    {
      display: "Projects",
      admin: true,
      click: () => dispatch(setTeam(isAdmin ? "" : user.team)),
    },
    { display: "Users", admin: true },
    { display: "Logout", link: "/", click: logout },
  ];

  return redirect ? (
    redirect
  ) : (
    <>
      <NothingSpace />
      <StyledNav showLinks={showing}>
        <div className="overlay" onClick={toggleShowing}></div>
        <NavLink to="/announcements">
          <img src={LogoImg} alt="logo" id="logo" />
        </NavLink>

        {isAdmin ? <p id="warning">Acting as Admin</p> : ""}

        <button id="menu-button" onClick={toggleShowing}>
          <img src={HamburgerImg} alt="menu" />
        </button>

        <ul id="links">
          {links.map(({ display, admin, link, click }, idx) =>
            admin === undefined || admin === isAdmin ? (
              <li onClick={toggleShowing} key={idx}>
                <NavLink to={link ? link : `/${display}`} onClick={click}>
                  {display}
                </NavLink>
              </li>
            ) : (
              ""
            )
          )}
        </ul>
      </StyledNav>
    </>
  );
};

export default NavBar;
