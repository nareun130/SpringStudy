import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createTodoApi, retrieveTodoApi, updateTodoApi } from "./api/TodoApiService";
import { useAuth } from "./security/AuthContext";
import { Field, Formik, Form, ErrorMessage } from "formik";
import moment from "moment";

export default function TodoComponent() {
  const { id } = useParams();
  const [description, setDescription] = useState("");
  const [targetDate, setTargetDate] = useState("");

  const authContext = useAuth();
  const navigate = useNavigate();
  const username = authContext.username;

  useEffect(() => retrieveTodos(), [id]);
               
  function retrieveTodos() {
    if (id != -1) {
      retrieveTodoApi(username, id)
        .then((response) => {
          setDescription(response.data.description);
          setTargetDate(response.data.targetDate);
        })
        .catch((error) => console.log(error));
    }
  }

  //* Form의 value들을 가져온다.
  async function onSubmit(values) {
    console.log(values);

    const todo = {
      id: id,
      username: username,
      description: values.description,
      targetDate: values.targetDate,
      done: false,
    };

    //!? harry
    // try {
    //   id === "-1" ? await createTodoApi(username, todo) : await updateTodoApi(username, id, todo)
    //   navigate("/todos")
    // } catch (e) {
    //   console.log(e)
    // }

    if (id == -1) {
      createTodoApi(username, todo)
        .then((response) => {
          navigate("/todos")
        })
        .catch((error) => console.log(error));
    } else {
      updateTodoApi(username, id, todo)
        .then((response) => {
          navigate("/todos")
        })
        .catch((error) => console.log(error));
    }
  }

  function validate(values) {
    const targetDate = new Date(values.targetDate).getTime();
    let errors = {
      //   description: "Enter a valid descrdiption",
      //   targetDate: "Enter a valid target date",
    };
    if (values.description.length < 5) errors.description = "Enter at least 5 characters";
    if (values.targetDate === "" || values.targetDate == null || !moment(values.targetDate).isValid()) errors.targetDate = "Enter a target date";
    if (targetDate <= Date.now()) errors.targetDate = "Enter a later date";

    return errors;
  }
  return (
    <div className="container">
      <h1>Enter Todo Details</h1>
      <div>
        <Formik
          initialValues={{ description, targetDate }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage name="description" component="div" className="alert alert-warning" />
              <ErrorMessage name="targetDate" component="div" className="alert alert-warning" />
              <fieldset className="form-group">
                <label>Description</label>
                <Field type="text" className="form-control" name="description"></Field>
              </fieldset>
              <fieldset className="form-group">
                <label>Target Date</label>
                <Field type="date" className="form-control" name="targetDate"></Field>
              </fieldset>
              <div>
                <button className="btn btn-success m-5" type="submit">
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}
