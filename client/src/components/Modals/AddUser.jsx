import { useState, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Button, FormGroup, Label } from "reactstrap";
import { Formik, ErrorMessage } from "formik";
import {
  StyledModal,
  StyledModalHeader,
  StyledModalBody,
  StyledButton,
  StyledField,
  StyledForm,
  StyledCloseButton,
} from "./Modals.module";

import BasicButton from "../ModalComponents/BasicButton";
import { addUser } from "../../utils/requests";
import { getCompany } from "../../reducers/rootReducer";

// const ErrorMessage = styled.p`
//   color: red;
// `;

const AddUser = ({ update }) => {
  const [modalOpen, setModalOpen] = useState(false);
  const [adminVal, setAdminVal] = useState("");

  const dispatch = useDispatch();
  const toggle = () => setModalOpen(!modalOpen);
  const setCompanyId = useSelector(getCompany);

  const handleAdmin = (e) => {
    setAdminVal(e.target.value);
  };

  const handleSubmit = (values) => {
    const user = {
      admin: adminVal,
      credentials: {
        password: values.password,
        username: values.username,
      },
      email: values.email,
      firstName: values.firstName,
      lastName: values.lastName,
      phone: values.phone,
      status: "PENDING",
      company: {
        id: setCompanyId.id,
      },
      // 'confirmPw': values.confirmPw,
    };
    console.log(values);
    addUser(user);
    setModalOpen(false);
    update();
  };

  const validateUserForm = (values) => {
    const errors = {};
    if (values.firstName.length < 2) {
      errors.firstName = "Missing first name";
    }
    if (values.lastName.length < 2) {
      errors.lastName = "Missing last name";
    }
    if (values.username.length < 2) {
      errors.username = "Missing username";
    }
    if (values.email.length < 2) {
      errors.email = "Missing email";
    }
    if (values.phone.length < 2) {
      errors.phone = "Missing phone number";
    }
    if (values.password.length < 4) {
      errors.password = "Password must be at least 2 characters";
    } else if (values.password.length > 15) {
      errors.password = "Password must be 15 characters or less";
    }
    if (values.confirmPw.length < 4) {
      errors.password = "Password must be at least 2 characters";
    } else if (values.confirmPw.length > 15) {
      errors.password = "Password must be 15 characters or less";
    }
    if (values.password != values.confirmPw) {
      errors.password = "Password and Confirm Password are not matching";
      errors.confirmPw = "Password and Confirm Password are not matching";
    }
    return errors;
  };

  return (
    <>
      <BasicButton w="auto" onClick={() => setModalOpen(true)}>
        Add User
      </BasicButton>
      <StyledModal isOpen={modalOpen} toggle={toggle}>
        <StyledModalHeader>
          Add User
          <StyledCloseButton color="danger" onClick={() => setModalOpen(false)}>
            X
          </StyledCloseButton>
        </StyledModalHeader>
        <StyledModalBody>
          <Formik
            enableReinitialize={true}
            initialValues={{
              firstName: "",
              lastName: "",
              email: "",
              phone: "",
              username: "",
              password: "",
              confirmPw: "",
            }}
            onSubmit={handleSubmit}
            validate={validateUserForm}
          >
            <StyledForm>
              <FormGroup>
                <Label htmlFor="firstName"></Label>
                <StyledField
                  name="firstName"
                  placeholder="First Name"
                  className="form-control"
                />
                <ErrorMessage
                  name="firstName"
                  component="p"
                  style={{ color: "red" }}
                />
              </FormGroup>
              <FormGroup>
                <Label htmlFor="lastName"></Label>
                <StyledField
                  name="lastName"
                  placeholder="Last Name"
                  className="form-control"
                />
                <ErrorMessage
                  name="lastName"
                  component="p"
                  style={{ color: "red" }}
                />
              </FormGroup>
              <FormGroup>
                <Label htmlFor="email"></Label>
                <StyledField
                  name="email"
                  placeholder="Email"
                  className="form-control"
                />
                <ErrorMessage
                  name="email"
                  component="p"
                  style={{ color: "red" }}
                />
                <Label htmlFor="phone"></Label>
                <StyledField
                  name="phone"
                  placeholder="Phone Number"
                  className="form-control"
                />
                <ErrorMessage
                  name="phone"
                  component="p"
                  style={{ color: "red" }}
                />
              </FormGroup>
              <FormGroup>
                <Label htmlFor="username"></Label>
                <StyledField
                  name="username"
                  placeholder="Username"
                  className="form-control"
                />
                <ErrorMessage
                  name="username"
                  component="p"
                  style={{ color: "red" }}
                />
              </FormGroup>
              <FormGroup>
                <Label htmlFor="password"></Label>
                <StyledField
                  name="password"
                  placeholder="Password"
                  className="form-control"
                />
                <ErrorMessage
                  name="password"
                  component="p"
                  style={{ color: "red" }}
                />
              </FormGroup>
              <FormGroup>
                <Label htmlFor="confirmPw"></Label>
                <StyledField
                  name="confirmPw"
                  placeholder="Confirm Password"
                  className="form-control"
                />
                <ErrorMessage
                  name="confirmPw"
                  component="p"
                  style={{ color: "red" }}
                />
              </FormGroup>
              <h2
                style={{
                  color: "rgb(222, 185, 146)",
                  margin: "4rem 0rem 2rem 0rem",
                }}
              >
                Make user an admin role?
              </h2>
              <Label htmlFor="admin"></Label>
              <select
                name="admin"
                className="form-control"
                value={adminVal}
                onChange={handleAdmin}
              >
                <option value={null}>Pick an option</option>
                <option value={true}>Yes</option>
                <option value={false}>No</option>
              </select>
              <StyledButton
                type="submit"
                color="primary"
                disabled={adminVal === ""}
              >
                Submit
              </StyledButton>
            </StyledForm>
          </Formik>
        </StyledModalBody>
      </StyledModal>
    </>
  );
};

export default AddUser;
