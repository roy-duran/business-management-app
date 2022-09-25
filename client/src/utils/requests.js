const ROOT_URL = "http://localhost:8080";

const handleData = async (data) => {
  if (data.status === 200) return data.json();

  return null;
};

const handleError = (error) => {
  console.log(`Error: ${error}`);
};

export const loginUser = async (credentials) => {
  return fetch(`${ROOT_URL}/user/validate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(credentials),
  })
    .then(handleData)
    .catch(handleError);
};

export const getCompanies = async () => {
  return fetch(`${ROOT_URL}/company`, {
    method: "GET",
  })
    .then(handleData)
    .catch(handleError);
};

export const getCompanyAnnouncements = async (companyId) => {
  return fetch(`${ROOT_URL}/announcements/${companyId}`, {
    method: "GET",
  })
    .then(handleData)
    .catch(handleError);
};

export const createNewAnnouncement = async (announcement) => {
  return fetch(`${ROOT_URL}/announcements/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(announcement),
  })
    .then(handleData)
    .catch(handleError);
};

export const addUser = async (user) => {
  return fetch(`${ROOT_URL}/user/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  })
    .then((response) => console.log(response))
    .then(handleData)
    .catch(handleError);
};

export const getAllUsersFromCompany = async (companyId) => {
  return fetch(`${ROOT_URL}/user/${companyId}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then(handleData)
    .catch(handleError);
};

export const createProject = async (newProject) => {
  return fetch(`${ROOT_URL}/project`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(newProject),
  })
    .then((response) => console.log(response))
    .then(handleData)
    .catch(handleError);
};

export const getAllProjects = async () => {
  return fetch(`${ROOT_URL}/project`, {
    method: "GET",
  })
    .then(handleData)
    .catch(handleError);
};

export const getAllTeams = async () => {
  return fetch(`${ROOT_URL}/team`, {
    method: "GET",
  })
    .then(handleData)
    .catch(handleError);
};

export const getAllProjectsByTeamId = async (teamID) => {
  return fetch(`${ROOT_URL}/project/team/${teamID}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then(handleData)
    .catch(handleError);
};

export const updateProject = async (newProject, replacedId) => {
  return fetch(`${ROOT_URL}/project/${replacedId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "no-cors",
    },
    body: JSON.stringify(newProject),
  })
    .then(handleData)
    .catch(handleError);
};

export const createTeam = async (teamData) => {
  return fetch(`${ROOT_URL}/team`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(teamData),
  })
    .then((response) => console.log(response))
    .then(handleData)
    .catch(handleError);
}


export const getAllTeamsAndProjectCountByCompany = async (companyId) => {
  return fetch(`${ROOT_URL}/team/teams/${companyId}`, {
    method: "GET",
  })
    .then(handleData)
    .catch(handleError);
};