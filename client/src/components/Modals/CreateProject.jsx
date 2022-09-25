import { useState, useEffect } from "react";
import { createProject } from "../../utils/requests";
import { getAllTeams } from "../../utils/requests";
import { updateProject } from "../../utils/requests";
import {
  StyledModal,
  StyledModalHeader,
  StyledModalBody,
  StyledCloseButton,
} from "./Modals.module";
import BasicButton from "../ModalComponents/BasicButton";

import styled from "styled-components";
import { useSelector } from "react-redux";
import { getCompany } from "../../reducers/rootReducer";

const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  gap: 1.5rem;
  padding-top: 2rem;
  & input,
  & textarea {
    width: 75%;
    margin: auto;
    background: inherit;
    border: none;
    border-bottom: 1px solid #deb998;
    padding: 0.5rem;
    font-size: 1rem;
    color: #deb998;
    ::placeholder {
      color: #deb998;
    }
  }
`;

const CreateProject = ({
  updatePage,
  buttonText,
  name = "",
  desc = "",
  team = "",
  projectID,
}) => {
  const company = useSelector(getCompany);
  const [modalOpen, setModalOpen] = useState(false);
  const [project, setProject] = useState({
    name,
    description: desc,
    teamId: team,
    active: true,
  });

  const [teams, setTeams] = useState([]);

  const toggle = () => setModalOpen(!modalOpen);

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (projectID) {
      await updateProject(project, projectID);
    } else {
      await createProject(project);
    }
    updatePage();
    setModalOpen(false);
  };

  useEffect(() => {
    (async () => {
      let DBTeams = (await getAllTeams()).filter(
        (team) => team?.teamCompany?.id === company?.id
      );

      setTeams(DBTeams);
      if (!team) setProject({ ...project, teamId: DBTeams[0]?.id });
    })();
  }, []);

  if (true)
    return (
      <>
        <BasicButton
          onClick={() => setModalOpen(true)}
          bg={team ? "#deb992" : undefined}
          c={team ? "black" : undefined}
        >
          {buttonText}
        </BasicButton>
        <StyledModal isOpen={modalOpen} toggle={toggle}>
          <StyledModalHeader>
            Create or Edit Project
            <StyledCloseButton
              color="danger"
              onClick={() => setModalOpen(false)}
            >
              X
            </StyledCloseButton>
          </StyledModalHeader>
          <StyledModalBody>
            <StyledForm onSubmit={handleSubmit}>
              <input
                type="text"
                name="name"
                value={project.name}
                onChange={(event) =>
                  setProject({ ...project, name: event.target.value })
                }
                placeholder="Project Name"
              />
              <textarea
                type="text"
                name="description"
                value={project.description}
                onChange={(event) =>
                  setProject({ ...project, description: event.target.value })
                }
                placeholder="Description"
                rows="5"
              />
              <select
                name="teamId"
                value={project.teamId}
                onChange={(event) =>
                  setProject({
                    ...project,
                    teamId: Number.parseInt(event.target.value),
                  })
                }
              >
                {teams.map(({ teamName, id }) => (
                  <option key={id} value={id}>
                    {teamName}
                  </option>
                ))}
              </select>
              <BasicButton type="submit">Submit</BasicButton>
            </StyledForm>
          </StyledModalBody>
        </StyledModal>
      </>
    );
};

export default CreateProject;
