const REDUX_STATE = "redux-state";

/* Action Types */
const ERASE_SESSION = "ERASE_SESSION";
const SET_CREDENTIALS = "SET_CREDENTIALS";
const SET_USERNAME = "SET_USERNAME";
const SET_PASSWORD = "SET_PASSWORD";
const SET_USER = "SET_USER";
const SET_COMPANY = "SET_COMPANY";
const SET_TEAM = "SET_TEAM";

const initialState = {
  credentials: {
    username: "",
    password: "",
  },
  user: "",
  company: "",
  team: "",
};

const loadState = () => {
  try {
    return {
      ...initialState,
      ...JSON.parse(localStorage.getItem(REDUX_STATE)),
    };
  } catch (err) {
    return initialState;
  }
};

export const saveState = (state) => {
  try {
    const serializedState = JSON.stringify(state);
    localStorage.setItem(REDUX_STATE, serializedState);
  } catch {
    // ignore errors
  }
};

/* Reducer Function */
const rootReducer = (state = loadState(), action) => {
  switch (action.type) {
    case ERASE_SESSION:
      return initialState;
    case SET_CREDENTIALS:
      return { ...state, credentials: action.credentials };
    case SET_USERNAME:
      return {
        ...state,
        credentials: { ...state.credentials, username: action.username },
      };
    case SET_PASSWORD:
      return {
        ...state,
        credentials: { ...state.credentials, password: action.password },
      };
    case SET_USER:
      return {
        ...state,
        user: action.user,
      };
    case SET_COMPANY:
      return {
        ...state,
        company: action.company,
      };
    case SET_TEAM:
      return {
        ...state,
        team: action.team,
      };
    default:
      return state;
  }
};

/* Action Creators */
export const eraseSession = () => ({
  type: ERASE_SESSION,
});

export const setCredentials = (credentials) => ({
  type: SET_CREDENTIALS,
  credentials,
});

export const setUsername = (username) => ({
  type: SET_USERNAME,
  username,
});

export const setPassword = (password) => ({
  type: SET_PASSWORD,
  password,
});

export const setUser = (user) => ({
  type: SET_USER,
  user,
});

export const setCompany = (company) => ({
  type: SET_COMPANY,
  company,
});

export const setTeam = (team) => ({
  type: SET_TEAM,
  team,
});

/* Selectors */
export const getCredentials = ({ credentials }) => credentials;

export const getUser = ({ user }) => user;

export const getCompany = ({ company }) => company;

export const getTeam = ({ team }) => team;

export const getAdmin = ({ user }) => user?.admin;

export default rootReducer;
