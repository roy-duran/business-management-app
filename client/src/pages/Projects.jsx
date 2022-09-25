import { Fragment, useState, useEffect } from "react";
import { useSelector } from "react-redux";
import styled from "styled-components";

import NavBar from "../components/NavBar";
import Project from "../components/Project";
import { getAllProjects, getAllProjectsByTeamId } from "../utils/requests";
import { getAdmin, getCompany, getTeam } from "../reducers/rootReducer";

const StyledProjects = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  & h1 {
    color: #1ba098;
    font-weight: 400;
    margin-top: 3rem;
  }
`;

const Projects = () => {
  const team = useSelector(getTeam);
  const company = useSelector(getCompany);
  const isAdmin = useSelector(getAdmin);

  const [projects, updateProjects] = useState([]);

  const handleGetProjects = async () => {
    const DBprojects = !team
      ? await getAllProjects()
      : await getAllProjectsByTeamId(team.id);

    updateProjects(
      DBprojects.filter(
        (project) => project?.teamOnProject?.teamCompany?.id === company?.id
      )
        .sort(
          (a, b) =>
            new Date(b.timePosted).getTime() - new Date(a.timePosted).getTime()
        )
        .map(({ name, timePosted, description, id, teamOnProject }) => ({
          id,
          name,
          description,
          teamID: teamOnProject.id,
          teamName: teamOnProject.teamName,
          editedDaysAgo: Math.round(
            (new Date().getTime() - new Date(timePosted).getTime()) /
              (1000 * 60 * 60 * 24)
          ),
        }))
    );
  };

  useEffect(() => {
    handleGetProjects();
  }, [team, company]);

  return (
    <Fragment>
      <NavBar />
      <StyledProjects>
        <h1>{team.teamName} Projects</h1>
        {isAdmin ? <Project updatePage={handleGetProjects} /> : ""}
        {projects.map(
          ({ name, editedDaysAgo, description, id, teamID, teamName }, idx) => (
            <Project
              name={name}
              editedDaysAgo={editedDaysAgo}
              description={description}
              id={id}
              teamID={teamID}
              teamName={teamName}
              updatePage={handleGetProjects}
              isAdmin={isAdmin}
              key={idx}
            />
          )
        )}
      </StyledProjects>
    </Fragment>
  );
};

export default Projects;
